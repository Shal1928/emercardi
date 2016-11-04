package ru.shal1928.emercardi.app.helpers;

import android.content.Context;
import android.content.Intent;
import ru.shal1928.emercardi.app.models.parts.IntentGetRuleRecord;
import ru.shal1928.emercardi.app.models.parts.IntentSetRuleRecord;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

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
                Object value = methods.getModelMethod().invoke(obj);
                methods.getIntentMethod().invoke(intent, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return intent;
    }

    public static <T> T getRealModel(Intent intent, Class cls) {
        Rule rule = getRule(cls);
        T realModel = (T) createInstance(cls);
        for(Map.Entry<String, Rule.Methods> entry:rule.setMethods.entrySet()) {
            try {
                Rule.Methods methods = entry.getValue();
                Object value = methods.getIntentMethod().invoke(intent);
                methods.getModelMethod().invoke(realModel, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return realModel;
    }

    public static class Rule {
        private Map<String, Methods> getMethods = new HashMap<>();
        private Map<String, Methods> setMethods = new HashMap<>();

        public Rule(Class cls) {
            for(Method method:cls.getMethods()) {
                Method modelMethod = method;

                IntentGetRuleRecord getRule = method.getAnnotation(IntentGetRuleRecord.class);
                if(getRule != null) {
                    Method intentMethod = getIntentMethod(method.getReturnType(), true);
                    getMethods.put(getRule.value(), new Methods(modelMethod, intentMethod));

                    continue;
                }

                IntentSetRuleRecord setRule = method.getAnnotation(IntentSetRuleRecord.class);
                if(setRule != null) {
                    if(method.getParameterTypes().length == 0) {
                        throw new RuntimeException("Set method from model can't be without parameters!");
                    }
                    Method intentMethod = getIntentMethod(method.getParameterTypes()[0], false);
                    setMethods.put(setRule.value(), new Methods(modelMethod, intentMethod));
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
                                    : Intent.class.getDeclaredMethod("getStringArrayExtra", String.class);
                        case "java.lang.Integer[]":
                        case "int[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Integer[].class)
                                    : Intent.class.getDeclaredMethod("getIntegerArrayExtra", String.class);
                        case "java.lang.Boolean[]":
                        case "boolean[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Boolean[].class)
                                    : Intent.class.getDeclaredMethod("getBooleanArrayExtra", String.class);
                        case "java.lang.Long[]":
                        case "long[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Long[].class)
                                    : Intent.class.getDeclaredMethod("getLongArrayExtra", String.class);
                        case "java.lang.Double[]":
                        case "double[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Double[].class)
                                    : Intent.class.getDeclaredMethod("getDoubleArrayExtra", String.class);
                        case "java.lang.Float[]":
                        case "float[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Float[].class)
                                    : Intent.class.getDeclaredMethod("getFloatArrayExtra", String.class);
                        case "java.lang.Short[]":
                        case "short[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Short[].class)
                                    : Intent.class.getDeclaredMethod("getShortArrayExtra", String.class);
                        case "java.lang.Byte[]":
                        case "byte[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Byte[].class)
                                    : Intent.class.getDeclaredMethod("getByteArrayExtra", String.class);
                        case "java.lang.Character[]":
                        case "char[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Character[].class)
                                    : Intent.class.getDeclaredMethod("getCharArrayExtra", String.class);
                        case "java.lang.CharSequence[]":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, CharSequence[].class)
                                    : Intent.class.getDeclaredMethod("getCharSequenceArrayExtra", String.class);
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
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Integer.class)
                                    : Intent.class.getDeclaredMethod("getIntegerExtra", String.class);
                        case "java.lang.Boolean":
                        case "boolean":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Boolean.class)
                                    : Intent.class.getDeclaredMethod("getBooleanExtra", String.class);
                        case "java.lang.Long":
                        case "long":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Long.class)
                                    : Intent.class.getDeclaredMethod("getLongExtra", String.class);
                        case "java.lang.Double":
                        case "double":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Double.class)
                                    : Intent.class.getDeclaredMethod("getDoubleExtra", String.class);
                        case "java.lang.Float":
                        case "float":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Float.class)
                                    : Intent.class.getDeclaredMethod("getFloatExtra", String.class);
                        case "java.lang.Short":
                        case "short":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Short.class)
                                    : Intent.class.getDeclaredMethod("getShortExtra", String.class);
                        case "java.lang.Byte":
                        case "byte":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Byte.class)
                                    : Intent.class.getDeclaredMethod("getByteExtra", String.class);
                        case "java.lang.Character":
                        case "char":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, Character.class)
                                    : Intent.class.getDeclaredMethod("getCharExtra", String.class);
                        case "java.lang.CharSequence":
                            return isGetRule
                                    ? Intent.class.getDeclaredMethod("putExtra", String.class, CharSequence.class)
                                    : Intent.class.getDeclaredMethod("getCharSequenceExtra", String.class);
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
            private Method modelMethod;
            private Method intentMethod;

            public Methods(Method modelMethod, Method intentMethod) {
                this.modelMethod = modelMethod;
                this.intentMethod = intentMethod;
            }

            public Method getModelMethod() {
                return modelMethod;
            }

            public void setModelMethod(Method modelMethod) {
                this.modelMethod = modelMethod;
            }

            public Method getIntentMethod() {
                return intentMethod;
            }

            public void setIntentMethod(Method intentMethod) {
                this.intentMethod = intentMethod;
            }
        }
    }

    private static <T> T createInstance(Class cls, Object... params) {
        String fullClassName = cls.getName();
        Class<?> targetClass;
        try {
            targetClass = Class.forName(fullClassName);
        } catch (ClassNotFoundException e) {
            String message = MessageFormat
                    .format("Во время загрузки класса по имени {0} произошло "
                            + "исключение!", fullClassName);
            throw new RuntimeException(message, e);
        }

        T result;
        try {
            result = params == null || params.length == 0
                    ? (T) targetClass.getConstructor().newInstance()
                    : (T) targetClass.getConstructor().newInstance(params);
        } catch (Exception e) {
            String message = MessageFormat.format("Во время создания "
                    + "экземпляра класса {0} произошло исключение!", fullClassName);
            throw new RuntimeException(message, e);
        }

        return result;
    }
}
