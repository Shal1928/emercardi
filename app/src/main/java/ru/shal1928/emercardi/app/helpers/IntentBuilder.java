package ru.shal1928.emercardi.app.helpers;

import android.content.Context;
import android.content.Intent;
import ru.shal1928.emercardi.app.models.parts.core.HasRule;
import ru.shal1928.emercardi.app.models.parts.core.IntentGetRuleRecord;
import ru.shal1928.emercardi.app.models.parts.core.IntentSetRuleRecord;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Danila on 01.11.2016.
 */
public class IntentBuilder {

    private Intent intent;

    private static final Map<String, Rule> RULES = new HashMap<>();

    public IntentBuilder() {
        this.intent = new Intent();
    }

    public IntentBuilder(Intent intent) {
        this.intent = intent;
    }

    public IntentBuilder(Context context, Class<?> cls) {
        this.intent = new Intent(context, cls);
    }

    private static Rule getRule(Class cls) {
        String className = cls.getCanonicalName();
        Rule rule = RULES.get(className);
        if(rule == null) {
            rule = new Rule(cls);
            RULES.put(className, rule);
        }

        return rule;
    }

    public Intent with(Object obj) {
        Rule rule = getRule(obj.getClass());
        for(Map.Entry<String, Rule.Methods> entry:rule.getMethods.entrySet()) {
            try {
                Rule.Methods methods = entry.getValue();

                Iterator iterator = methods.getModelMethods().iterator();
                Object val = iterator.hasNext() ? getNextValueLevel(obj, iterator) : null;

                methods.getIntentMethod().invoke(intent, entry.getKey(), val);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        return intent;
    }

    private Object getNextValueLevel(Object obj, Iterator iteratorMethod){
        try {
            Object value = ((Method)iteratorMethod.next()).invoke(obj);
            if(iteratorMethod.hasNext()) {
                value = getNextValueLevel(value, iteratorMethod);
            }

            return value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object value;
    public static <T> T getRealModel(Intent intent, Class cls) {
        Rule rule = getRule(cls);
        T realModel = (T) createInstance(cls);
        for(Map.Entry<String, Rule.Methods> entry:rule.setMethods.entrySet()) {
            try {
                Rule.Methods methods = entry.getValue();
                String property = entry.getKey();

                if(methods.getInstanceOfReturnType() instanceof String) {
                    value = methods.getIntentMethod().invoke(intent, property);
                } else {
                    value = methods.getIntentMethod().invoke(intent, property, methods.getInstanceOfReturnType());
                }

                Iterator<Method> iterator = methods.getModelMethods().iterator();
                if(iterator.hasNext()) {

                    Method setModelMethod = iterator.next();
                    switch (setModelMethod.getParameterTypes()[0].getCanonicalName()) {
                        case "java.util.Calendar":
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis((long)value);
                            value = calendar;
                            break;
                        default:
                            //
                            break;
                    }

                    setModelMethod.invoke(realModel, value);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return realModel;
    }

    public static class Rule {
        private Map<String, Methods> getMethods = new HashMap<>();
        private Map<String, Methods> setMethods = new HashMap<>();

        private Class getHasRuleCls(Class cls){
            Class result = null;
            if(cls.getAnnotation(HasRule.class) == null) {
                for(Class i:cls.getInterfaces()) {
                    if(i.getAnnotation(HasRule.class) != null){
                        result = i;
                        break;
                    }
                }
            } else {
                result = cls;
            }

            return result;
        }

        public Rule(Class cls) {
            cls = getHasRuleCls(cls);

            for(Method method:cls.getMethods()) {
                Collection<Method> modelMethods = new ArrayList<>();
                modelMethods.add(method);

                IntentGetRuleRecord getRule = method.getAnnotation(IntentGetRuleRecord.class);
                if(getRule != null) {
                    Class returnType = method.getReturnType();
                    //Получаем метод для получения значение из объекта Intent,
                    //по типу возвращаемого значения объекта модели
                    Method intentMethod = getIntentMethod(returnType, true);

                    String[] getModelMethods = getRule.getModelMethods();
                    if(getModelMethods != null && getModelMethods.length > 0) {
                        for(String getModelMethod:getModelMethods) {
                            if(getModelMethod.isEmpty()) {
                                continue;
                            }

                            try {
                                modelMethods.add(returnType.getMethod(getModelMethod));
                            } catch (NoSuchMethodException e) {
                                String message = MessageFormat.format(
                                        "Во время получения специфического геттера {0} класса {1} произошло исключение!",
                                        getModelMethod, returnType.getName());
                                throw new RuntimeException(message, e);
                            }
                        }
                    }

                    getMethods.put(getRule.value(), new Methods(intentMethod, modelMethods.toArray(new Method[0])));

                    continue;
                }

                IntentSetRuleRecord setRule = method.getAnnotation(IntentSetRuleRecord.class);
                if(setRule != null) {
                    if(method.getParameterTypes().length == 0) {
                        throw new RuntimeException("Set method from model can't be without parameters!");
                    }
                    Method intentMethod = getIntentMethod(method.getParameterTypes()[0], false);
                    setMethods.put(setRule.value(), new Methods(intentMethod, modelMethods.toArray(new Method[0])));
                }
            }
        }

        private Method getIntentMethod(Class cls, boolean isGetRule) {
            String canName = cls.getCanonicalName();
            try {
                if(cls.isArray()) {
                    switch (canName) {
                        case "java.lang.String[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, String[].class)
                                    : Intent.class.getDeclaredMethod("getStringArrayListExtra", String.class);
                        case "java.lang.Integer[]":
                        case "int[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, int[].class)
                                    : Intent.class.getDeclaredMethod("getIntArrayListExtra",
                                    String.class, int[].class);
                        case "java.lang.Boolean[]":
                        case "boolean[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, boolean[].class)
                                    : Intent.class.getDeclaredMethod("getBooleanArrayListExtra",
                                    String.class, boolean[].class);
                        case "java.lang.Long[]":
                        case "long[]":
                        case "java.util.Calendar[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, long[].class)
                                    : Intent.class.getDeclaredMethod("getLongArrayListExtra",
                                    String.class, long[].class);
                        case "java.lang.Double[]":
                        case "double[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, double[].class)
                                    : Intent.class.getDeclaredMethod("getDoubleArrayListExtra",
                                    String.class, double[].class);
                        case "java.lang.Float[]":
                        case "float[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, float[].class)
                                    : Intent.class.getDeclaredMethod("getFloatArrayListExtra",
                                    String.class, float[].class);
                        case "java.lang.Short[]":
                        case "short[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, short[].class)
                                    : Intent.class.getDeclaredMethod("getShortArrayListExtra",
                                    String.class, short[].class);
                        case "java.lang.Byte[]":
                        case "byte[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, byte[].class)
                                    : Intent.class.getDeclaredMethod("getByteArrayListExtra",
                                    String.class, byte[].class);
                        case "java.lang.Character[]":
                        case "char[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, char[].class)
                                    : Intent.class.getDeclaredMethod("getCharArrayListExtra",
                                    String.class, char[].class);
                        case "java.lang.CharSequence[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, CharSequence[].class)
                                    : Intent.class.getDeclaredMethod("getCharSequenceArrayListExtra",
                                    String.class, CharSequence[].class);
                        default:
                            break;
                    }
                } else {
                    switch (canName) {
                        case "java.lang.String":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, String.class)
                                    : Intent.class.getDeclaredMethod("getStringExtra", String.class);
                        case "java.lang.Integer":
                        case "int":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, int.class)
                                    : Intent.class.getDeclaredMethod("getIntExtra", String.class, int.class);
                        case "java.lang.Boolean":
                        case "boolean":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, boolean.class)
                                    : Intent.class.getDeclaredMethod("getBooleanExtra", String.class, boolean.class);
                        case "java.lang.Long":
                        case "long":
                        case "java.util.Calendar":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, long.class)
                                    : Intent.class.getDeclaredMethod("getLongExtra", String.class, long.class);
                        case "java.lang.Double":
                        case "double":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, double.class)
                                    : Intent.class.getDeclaredMethod("getDoubleExtra", String.class, double.class);
                        case "java.lang.Float":
                        case "float":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, float.class)
                                    : Intent.class.getDeclaredMethod("getFloatExtra", String.class, float.class);
                        case "java.lang.Short":
                        case "short":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, short.class)
                                    : Intent.class.getDeclaredMethod("getShortExtra", String.class, short.class);
                        case "java.lang.Byte":
                        case "byte":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, byte.class)
                                    : Intent.class.getDeclaredMethod("getByteExtra", String.class, byte.class);
                        case "java.lang.Character":
                        case "char":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, char.class)
                                    : Intent.class.getDeclaredMethod("getCharExtra", String.class, char.class);
                        case "java.lang.CharSequence":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, CharSequence.class)
                                    : Intent.class.getDeclaredMethod("getCharSequenceExtra", String.class, CharSequence.class);
                        default:
                            break;
                    }
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            throw new RuntimeException(MessageFormat.format("Type {0} for Intent is not supported!",
                    StringHelper.addDQuote(cls.getSimpleName())));
        }

        private class Methods {
            private Collection<Method> modelMethods = new ArrayList<>();
            private Method intentMethod;

            public Object getInstanceOfReturnType(){
                Class cls = intentMethod.getReturnType();
                String canName = cls.getCanonicalName();
                if(cls.isArray()) {
                    switch (canName) {
                        case "java.lang.String[]": return new String[0];
                        case "java.lang.Integer[]":
                        case "int[]": return new Integer[0];
                        case "java.lang.Boolean[]":
                        case "boolean[]": return new Boolean[0];
                        case "java.lang.Long[]":
                        case "long[]":
                        case "java.util.Calendar[]": return new Long[0];
                        case "java.lang.Double[]":
                        case "double[]": return new Double[0];
                        case "java.lang.Float[]":
                        case "float[]": return new Float[0];
                        case "java.lang.Short[]":
                        case "short[]": return new Short[0];
                        case "java.lang.Byte[]":
                        case "byte[]": return new Byte[0];
                        case "java.lang.Character[]":
                        case "char[]": return new Character[0];
                        case "java.lang.CharSequence[]": return new CharSequence[0];
                        default:
                            return new Object[0];
                    }
                } else {
                    switch (canName) {
                        case "java.lang.String": return new String();
                        case "java.lang.Integer":
                        case "int": return new Integer(0);
                        case "java.lang.Boolean": return new Boolean(false);
                        case "java.lang.Long":
                        case "long":
                        case "java.util.Calendar": return new Long(0);
                        case "java.lang.Double":
                        case "double": return new Double(0);
                        case "java.lang.Float":
                        case "float": return new Float(0);
                        case "java.lang.Short":
                        case "short": return new Short(null);
                        case "java.lang.Byte": return new Byte(null);
                        case "java.lang.Character":
                        case "char": return new Character('a');
                        case "java.lang.CharSequence": return new CharSequence() {
                            @Override public int length() {
                                return 0;
                            }

                            @Override public char charAt(int index) {
                                return 0;
                            }

                            @Override public CharSequence subSequence(int start, int end) {
                                return null;
                            }

                            @Override public String toString() {
                                return null;
                            }
                        };
                        default:
                            return new Object();
                    }
                }
            }

            public Methods(Method intentMethod, Method... modelMethods) {
                this.intentMethod = intentMethod;
                this.modelMethods = new ArrayList(Arrays.asList(modelMethods));
            }

            public Collection<Method> getModelMethods() {
                return modelMethods;
            }
            public Method getIntentMethod() {
                return intentMethod;
            }
        }
    }

    private static <T> T createInstance(Class cls, Object... params) {
        String fullClassName = cls.getName();
        Class<?> targetClass;
        try {
            targetClass = Class.forName(fullClassName);
        } catch (ClassNotFoundException e) {
            String message = MessageFormat.format("Во время загрузки класса по имени {0} произошло исключение!",
                    fullClassName);
            throw new RuntimeException(message, e);
        }

        T result;
        try {
            result = params == null || params.length == 0
                    ? (T) targetClass.getConstructor().newInstance()
                    : (T) targetClass.getConstructor().newInstance(params);
        } catch (Exception e) {
            String message = MessageFormat.format("Во время создания экземпляра класса {0} произошло исключение!",
                    fullClassName);
            throw new RuntimeException(message, e);
        }

        return result;
    }
}
