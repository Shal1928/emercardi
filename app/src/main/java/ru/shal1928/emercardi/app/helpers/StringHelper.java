package ru.shal1928.emercardi.app.helpers;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.text.MessageFormat.format;

/**
 * Created by Danila on 02.11.2016.
 */
public class StringHelper {

    //region ForceFormat
    private static Matcher getForceFormatMatcher(int index, String message){
        String regex = new StringBuilder().append("\\").append("{").append(index).append("\\").append("}").toString();
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        return p.matcher(message);
    }

    public static String forceFormat(String message, Collection params) {
        return forceFormat(message, params.toArray());
    }

    /***
     * Форматирование строки, которая задана переменной
     * @param message Переменная строки
     * @param params Параметры для вставки
     * @return Форматированная строка, со вставленными значениями, указанными индексами ({0}{1}{2}...)
     */
    public static String forceFormat(String message, Object... params){
        if(params == null || params.length == 0) return message;

        for(int i = 0; i < params.length; i++){
            Matcher m = getForceFormatMatcher(i, message);
            message = m.replaceAll(String.valueOf(params[i]));
        }

        return message;
    }
    //endregion

    //region GetLastIndex
    public static int getLastIndex(String value){
        return value != null
                ? value.length() - 1
                : -1;
    }

    public static int getLastIndex(StringBuilder value){
        return value != null
                ? value.length() - 1
                : -1;
    }
    //endregion

    //region GetLastSymbol
    public static String getLastSymbol(String value){
        return value.substring(getLastIndex(value));
    }

    public static String getLastSymbol(StringBuilder value){
        return value.substring(getLastIndex(value));
    }
    //endregion

    //region DeleteLastSymbol
    public static String deleteLastSymbol(String value){
        return value.substring(1, getLastIndex(value));
    }

    public static String deleteLastSymbol(StringBuilder value){
        return String.valueOf(value.deleteCharAt(getLastIndex(value)));
    }
    //endregion

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNullOrEmpty(StringBuilder value) {
        return value == null || value.length() == 0;
    }

    public static String asNotNullString(Object value){
        return value == null ? "" : String.valueOf(value);
    }

    //region AddMark Methods

    /**
     * Обрамляет строку знаками
     * @param leftMark Левый знак
     * @param core Строка
     * @param rightMark Правый знак
     * @return leftMark+core+rightMark
     */
    public static String addMark(String leftMark, String core, String rightMark) {
        return MessageFormat.format("{0}{1}{2}", leftMark, core, rightMark);
    }

    /**
     * Обрамляет строку знаками
     * @param core Строка
     * @param mark Знак
     * @return mark+core+mark
     */
    public static String addMark(String core, String mark) {
        return addMark(mark, core, mark);
    }

    /**
     * Обрамляет строку одинарными кавычками
     * @param core Строка
     * @return 'core'
     */
    public static String addSQuote(String core) {
        return addMark(core, "\'");
    }

    /**
     * Обрамляет строку двойными кавычками
     * @param core Строка
     * @return "core"
     */
    public static String addDQuote(String core) {
        return addMark(core, "\"");
    }

    /**
     * Обрамляет строку клювиками (знаки больше, меньше)
     * @param core Строка
     * @return <core>
     */
    public static String addBeak(String core) {
        return addMark("<", core, ">");
    }

    /**
     * Обрамляет строку скобками
     * @param core Строка
     * @return (core)
     */
    public static String addBkt(String core) {
        return addMark("(", core, ")");
    }

    /**
     * Обрамляет строку квадратными скобками
     * @param core Строка
     * @return [core]
     */
    public static String addSqBkt(String core) {
        return addMark("[", core, "]");
    }

    /**
     * Обрамляет строку фигурными скобками
     * @param core Строка
     * @return {core}
     */
    public static String addBraces(String core) {
        return addMark("{", core, "}");
    }

    /**
     * Обрамляет строку звездочками
     * @param core Строка
     * @return *core*
     */
    public static String addStars(String core) {
        return addMark(core, "*");
    }

    /**
     * Обрамляет строку знаками минуса
     * @param core Строка
     * @return -core-
     */
    public static String addMinuses(String core) {
        return addMark(core, "-");
    }

    /**
     * Обрамляет строку знаками минуса
     * @param core Строка
     * @return -core-
     */
    public static String addPercents(String core) {
        return addMark(core, "%");
    }

    /**
     * Обрамляет строку знаками минуса
     * @param core Строка
     * @return -core-
     */
    public static String addSpaces(String core) {
        return addMark(core, " ");
    }

    //endregion


    public static String getTwoDigitTimeValue(String timeValue){
        if(isNullOrEmpty(timeValue)) return "00";
        if(timeValue.length()>=2) return timeValue;
        return format("0{0}", timeValue);
    }
}
