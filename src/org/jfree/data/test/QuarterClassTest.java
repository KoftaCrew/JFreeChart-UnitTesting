package org.jfree.data.test;

import org.jfree.data.time.Quarter;
import org.jfree.data.time.Year;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QuarterClassTest {
    Quarter quarter;

    private void arrange(Integer quart, Integer year) {
        quarter = new Quarter(quart, year);
    }

    //provided private methods for constructor testing
    private void arrange() {
        quarter = new Quarter();
    }

    private void arrange(Date time) {
        quarter = new Quarter(time);
    }

    private void arrange(java.util.Date time, TimeZone zone) {
        quarter = new Quarter(time, zone);
    }

    private void arrange(int quarterInt, int year) {
        quarter = new Quarter(quarterInt, year);
    }

    private void arrange(int quarterInt, Year year) {
        quarter = new Quarter(quarterInt, year);
    }

    //Smoke Testing on Constructors
    @Test
    public void testQuarterDefaultCtor() {
        arrange();
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    @Test
    public void testQuarterDateCtor() {
        arrange(Date.from(Instant.now()));
        assertEquals(quarter.getYear().getYear(), 2023);
        assertEquals(quarter.getQuarter(), 2);
    }

    @Test
    public void testQuarterDateTimezoneCtor() {
        arrange(Date.from(Instant.now()), TimeZone.getTimeZone("GMT +2:00"));
        assertEquals(quarter.getQuarter(), 2);
        assertEquals(quarter.getYear().getYear(), 2023);

    }

    @Test
    public void testQuarterIntCtor() {
        arrange(2, 2024);
        assertEquals(quarter.getQuarter(), 2);
        assertEquals(quarter.getYear().getYear(), 2024);
    }

    @Test
    public void testQuarterYearCtor() {
        arrange(2, Year.parseYear("2024"));
        assertEquals(quarter.getQuarter(), 2);
        assertEquals(quarter.getYear().getYear(), 2024);
    }

    @Test
    public void testQuarterFields() {
        assertEquals(Quarter.FIRST_QUARTER, 1);
        assertEquals(Quarter.FIRST_MONTH_IN_QUARTER[1], 1);
        assertEquals(Quarter.FIRST_MONTH_IN_QUARTER[2], 4);
        assertEquals(Quarter.FIRST_MONTH_IN_QUARTER[3], 7);
        assertEquals(Quarter.FIRST_MONTH_IN_QUARTER[4], 10);

        assertEquals(Quarter.LAST_QUARTER, 4);
        assertEquals(Quarter.LAST_MONTH_IN_QUARTER[1], 3);
        assertEquals(Quarter.LAST_MONTH_IN_QUARTER[2], 6);
        assertEquals(Quarter.LAST_MONTH_IN_QUARTER[3], 9);
        assertEquals(Quarter.LAST_MONTH_IN_QUARTER[4], 12);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    //Int Ctor Testing
    @Test
    public void testQuarterYearOutofBoundsLowIntCtor() { // fails due to a documentation bug

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Year constructor: year (999) outside valid range.");
        arrange(3, 999);
    }

    @Test
    public void testQuarterYearOutOfBoundsHighIntCtor() { //fails due to documentation bug

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Year constructor: year (10000) outside valid range.");
        arrange(3, 10000);
    }

    @Test
    public void testQuarterYearLowLimitIntCtor() {
        arrange(1, 1900);
        assertEquals(quarter.getYear().getYear(), 1900);
    }

    @Test
    public void testQuarterYearHighLimitIntCtor() {
        arrange(4, 9999);
        assertEquals(quarter.getYear().getYear(), 9999);
    }

    @Test
    public void testQuarterQuarterOutofBoundsLowIntCtor() { // fails due to a bug in documentation //should throw IllegalArgumentException
        arrange(0, 2023);
        assertFalse(quarter.getQuarter() == 0);
    }

    @Test
    public void testQuarterQuarterOutofBoundsHighIntCtor() { // fails due to a bug in documentation //should throw IllegalArgumentException
        arrange(5, 2023);
        assertFalse(quarter.getQuarter() == 5);
    }

    @Test
    public void testQuarterQuarterLowLimitIntCtor() {
        arrange(1, 2023);
        assertEquals(quarter.getQuarter(), 1);
    }

    @Test
    public void testQuarterQuarterHighLimitIntCtor() {
        arrange(4, 2023);
        assertEquals(quarter.getQuarter(), 4);
    }

    //Year Ctor Testing
    @Test
    public void testQuarterYearOutofBoundsLowYearCtor() { //bug in documentation
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("year (1899) outside valid range.");
        arrange(2, new Year(1899));
    }

    @Test
    public void testQuarterYearOutofBoundsHighYearCtor() { //bug in documentation
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("year (10000) outside valid range.");
        arrange(2, new Year(10000));
    }

    @Test
    public void testQuarterYearLowLimitYearCtor() {
        arrange(2, new Year(1900));
        assertEquals(quarter.getYear().getYear(), 1900);
    }

    @Test
    public void testQuarterYearHighLimitYearCtor() {
        arrange(2, new Year(9999));
        assertEquals(quarter.getYear().getYear(), 9999);
    }

    @Test
    public void testQuarterQuarterOutofBoundslowYearCtor() { //should throw IllegalArgumentException
        arrange(0, new Year(2023));
        assertFalse(quarter.getQuarter() == 0);
    }

    @Test
    public void testQuarterQuarterOutofBoundsHighYearCtor() {//should throw IllegalArgumentException
        arrange(5, new Year(2023));
        assertFalse(quarter.getQuarter() == 5);
    }

    @Test
    public void testQuarterQuarterLowLimitYearCtor() {
        arrange(1, new Year(2023));
        assertEquals(quarter.getQuarter(), 1);
    }

    @Test
    public void testQuarterQuarterHighLimitYearCtor() {
        arrange(4, new Year(2023));
        assertEquals(quarter.getQuarter(), 4);
    }
    @Test
    public void testQuarterYearOutofBoundLowDateCtor() { //bug in documentation?
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Year constructor: year (1899) outside valid range.");
        arrange(Date.from(Instant.parse("1899-01-01T00:00:00Z")));
    }

    @Test
    public void testQuarterYearOutofBoundHighDateCtor() { //bug in documentation?
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Year constructor: year (10000) outside valid range.");
        arrange(Date.from(Instant.parse("10000-01-01T00:00:00Z")));
    }

    @Test
    public void testQuarterYearLowLimitDateCtor() {
        arrange(Date.from(Instant.parse("1900-01-01T00:00:00Z")));
        assertEquals(quarter.getYear().getYear(), 1900);
        assertEquals(quarter.getQuarter(), 1);
    }

    @Test
    public void testQuarterYearHighLimitDateCtor() {
        arrange(Date.from(Instant.parse("9999-12-31T00:00:00Z")));
        assertEquals(quarter.getYear().getYear(), 9999);
        assertEquals(quarter.getQuarter(), 4);
    }

    @Test
    public void testQuarterQuarterOutofBoundsLowDateCtor() {
        exceptionRule.expect(java.time.format.DateTimeParseException.class);
        exceptionRule.expectMessage("Text '2023-00-00T00:00:00Z' could not be parsed at index 0");
        arrange(Date.from(Instant.parse("2023-00-00T00:00:00Z")));
    }

    @Test
    public void testQuarterQuarterOutofBoundsHighDateCtor() {
        exceptionRule.expect(java.time.format.DateTimeParseException.class);
        exceptionRule.expectMessage("Text '2023-13-01T00:00:00Z' could not be parsed at index 0");
        arrange(Date.from(Instant.parse("2023-13-01T00:00:00Z")));
    }

    @Test
    public  void testQuarterQuarterLowLimitDateCtor() {
        arrange(Date.from(Instant.parse("2023-01-01T00:00:00Z")));
        assertEquals(quarter.getQuarter(), 1);
    }

    @Test
    public  void testQuarterQuarterHighLimitDateCtor() {
        arrange(Date.from(Instant.parse("2023-12-31T00:00:00Z")));
        assertEquals(quarter.getQuarter(), 4);
    }

    @Test
    public void testQuarterYearOutofBoundsLowDateTimezoneCtor() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Year constructor: year (1899) outside valid range.");
        arrange(Date.from(Instant.parse("1899-01-01T00:00:00Z")), TimeZone.getTimeZone("GMT +2:00"));
    }

    @Test
    public void testQuarterYearOutofBoundsHighDateTimezoneCtor() { //bug in documentation?
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Year constructor: year (10000) outside valid range.");
        arrange(Date.from(Instant.parse("10000-01-01T00:00:00Z")), TimeZone.getTimeZone("GMT +2:00"));
    }

    @Test
    public void testQuarterYearLowLimitDateTimezoneCtor() {
        arrange(Date.from(Instant.parse("1900-01-01T00:00:00Z")), TimeZone.getTimeZone("GMT +2:00"));
        assertEquals(quarter.getYear().getYear(), 1900);
        assertEquals(quarter.getQuarter(), 1);
    }

    @Test
    public void testQuarterYearHighLimitDateTimezoneCtor() {
        arrange(Date.from(Instant.parse("9999-12-31T00:00:00Z")), TimeZone.getTimeZone("GMT +2:00"));
        assertEquals(quarter.getYear().getYear(), 9999);
        assertEquals(quarter.getQuarter(), 4);
    }

    //Method Testing
    @Test
    public void testGetQuarter() {
        arrange(2, 2023);
        assertEquals(quarter.getQuarter(), 2);
    }

    @Test
    public void testGetYear() {
        arrange(2, 2023);
        assertEquals(new Year(), new Year(2023));
    }

    //previous Method tests
    @Test
    public void testPreviousFirstQuarter() {
        arrange(1, 1900);
        assertNull(quarter.previous());
    }

    @Test
    public void testPreviousLastQuarter() {
        arrange(4, 9999);
        Quarter temp = new Quarter(3, 9999);
        assertEquals(quarter.previous(), temp);
    }

    @Test
    public void testPreviousAcrossYear() {
        arrange(1, 2024);
        Quarter temp = new Quarter(4, 2023);
        assertEquals(quarter.previous(), temp);
    }

    @Test
    public void testPreviousMidYear() {
        arrange(4, 2023);
        Quarter temp = new Quarter(3, 2023);
        assertEquals(quarter.previous(), temp);
    }

    //next Method tests
    @Test
    public void testNextFirstQuarter() {
        arrange(1, 1900);
        Quarter temp = new Quarter(2, 1900);
        assertEquals(quarter.next(), temp);
    }

    @Test
    public void testNextLastQuarter() {
        arrange(4, 9999);
        assertNull(quarter.next());
    }

    @Test
    public void testNextAcrossYear() {
        arrange(4, 2023);
        Quarter temp = new Quarter(1, 2024);
        assertEquals(quarter.next(), temp);
    }

    @Test
    public void testNextMidYear() {
        arrange(2, 2023);
        Quarter temp = new Quarter(3, 2023);
        assertEquals(quarter.next(), temp);
    }

    @Test
    public void testGetSerialIndex() {
        arrange(2, 2023);
        assertEquals(quarter.getSerialIndex(), 2023 * 4 + 2);
    }

    //equals Method tests
    @Test
    public void testEqualsSameObject() {
        arrange(2, 2023);
        assertEquals(quarter, quarter);
    }

    @Test
    public void testEqualsCopyObjectTrue() {
        arrange(2, 2023);
        Quarter temp = quarter;
        assertEquals(quarter, temp);
    }

    @Test
    public void testEqualsCopyObjectFalse() {
        arrange(2, 2023);
        Quarter temp = quarter;
        temp = (Quarter) quarter.next();
        assertFalse(temp.equals(quarter));
    }

    @Test
    public void testEqualsQuarterDiffObjectTrue() {
        Quarter temp = new Quarter(2, 2023);
        arrange(2, 2023);
        assertEquals(quarter, temp);
    }

    @Test
    public void testEqualsQuarterDiffObjectFalse() {
        Quarter temp = new Quarter(3, 2023);
        arrange(2, 2023);
        assertFalse(quarter.equals(temp));
    }

    @Test
    public void testEqualsYearDiffObjectFalse() {
        arrange(2, 2023);
        Quarter temp = new Quarter(2, 1900);
        assertFalse(temp.equals(quarter));
    }

    @Test
    public void testEqualsYearQuarterDiffObject() {
        arrange(2, 2023);
        Quarter temp = new Quarter(3, 2021);
        assertFalse(temp.equals(quarter));
    }

    //hashCode Method Tests
    @Test
    public void testHashCodeSameObject() {
        arrange(2, 2023);
        assertEquals(quarter.hashCode(), quarter.hashCode());
    }

    @Test
    public void testHashCodeCopyObjectTrue() {
        arrange(2, 2023);
        Quarter temp = quarter;
        assertEquals(temp.hashCode(), quarter.hashCode());
    }

    @Test
    public void testHashCodeDiffObjectFalse() { //flakey test
        arrange(2, 2023);
        Quarter temp = new Quarter(3, 2023);
        assertFalse(temp.hashCode() == quarter.hashCode());
    }

    @Test
    public void testHashCodeDiffObjectTrue() {
        arrange(2, 2023);
        Quarter temp = new Quarter(2, 2023);
        assertEquals(temp.hashCode(), quarter.hashCode());
    }

    //compareTo Method Tests
    @Test
    public void testCompareToSameValue() {
        arrange(2, 2023);
        Quarter temp = new Quarter(2, 2023);
        assertEquals(temp.compareTo(quarter), 0);
    }

    @Test
    public void testCompareToNegativeValue() {
        arrange(2, 2023);
        Quarter temp = new Quarter(1, 2023);
        assertTrue(temp.compareTo(quarter) < 0);
    }

    @Test
    public void testCompareToPositiveValue() {
        arrange(2, 2023);
        Quarter temp = new Quarter(3, 2023);
        assertTrue(temp.compareTo(quarter) > 0);
    }

    @Test
    public void testCompareToDifferentYearsNegative() {
        arrange(2, 2023);
        Quarter temp = new Quarter(2, 2022);
        assertTrue(temp.compareTo(quarter) < 0);
    }

    @Test
    public void testCompareToDifferentYearsPositive() {
        arrange(2, 2023);
        Quarter temp = new Quarter(2, 2024);
        assertTrue(temp.compareTo(quarter) > 0);
    }

    //toString Method Tests
    @Test
    public void testToString() {
        arrange(2, 2023);
        assertEquals(quarter.toString(), "Q2/2023");
    }

    //getFirstMillisecond Method Tests
    @Test
    public void testGetFirstMillisecondQuarterMid() {
        arrange(2, 2023);
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.APRIL, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        assertEquals(quarter.getFirstMillisecond(), cal.getTimeInMillis());
    }

    @Test
    public void testGetFirstMillisecondFirstQuarter() {
        arrange(1, 1900);
        Calendar cal = Calendar.getInstance();
        cal.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        assertEquals(quarter.getFirstMillisecond(), cal.getTimeInMillis());
    }

    @Test
    public void testGetFirstMillisecondLastQuarter() {
        arrange(4, 9999);
        Calendar cal = Calendar.getInstance();
        cal.set(9999, Calendar.OCTOBER, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        assertEquals(quarter.getFirstMillisecond(), cal.getTimeInMillis());
    }

    //getLastMillisecond Method Tests
    @Test
    public void testGetLastMillisecondMid() {
        arrange(2, 2023);
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.JUNE, 30, 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 999);
        assertEquals(quarter.getLastMillisecond(), cal.getTimeInMillis());
    }

    @Test
    public void testGetLastMillisecondFirstQuarter() {
        arrange(1, 1900);
        Calendar cal = Calendar.getInstance();
        cal.set(1900, Calendar.MARCH, 31, 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 999);
        assertEquals(quarter.getLastMillisecond(), cal.getTimeInMillis());
    }

    @Test
    public void testGetLastMillisecondLastQuarter() {
        arrange(4, 9999);
        Calendar cal = Calendar.getInstance();
        cal.set(9999, Calendar.DECEMBER, 31, 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 999);
        assertEquals(quarter.getLastMillisecond(), cal.getTimeInMillis());
    }

    //parseQuarter Method Tests
    @Test
    public void testParseQuarterSlashYearFirst() {
        Quarter temp = Quarter.parseQuarter("2023/Q2");
        arrange(2, 2023);
        assertEquals(temp, quarter);
    }

    @Test
    public void testParseQuarterSlashYearLast() {
        Quarter temp = Quarter.parseQuarter("Q2/2023");
        arrange(2, 2023);
        assertEquals(temp, quarter);
    }

    @Test
    public void testParseQuarterCommaYearFirst() {
        Quarter temp = Quarter.parseQuarter("2023,Q2");
        arrange(2, 2023);
        assertEquals(temp, quarter);
    }

    @Test
    public void testParseQuarterCommaYearLast() {
        Quarter temp = Quarter.parseQuarter("Q2,2023");
        arrange(2, 2023);
        assertEquals(temp, quarter);
    }

    @Test
    public void testParseQuarterDashYearFirst() {
        Quarter temp = Quarter.parseQuarter("2023-Q2");
        arrange(2, 2023);
        assertEquals(temp, quarter);
    }

    @Test
    public void testParseQuarterDashYearLast() {
        Quarter temp = Quarter.parseQuarter("Q2-2023");
        arrange(2, 2023);
        assertEquals(temp, quarter);
    }

    @Test
    public void testParseQuarterSpaceYearFirst() {
        Quarter temp = Quarter.parseQuarter("2023 Q2");
        arrange(2, 2023);
        assertEquals(temp, quarter);
    }

    @Test
    public void testParseQuarterSpaceYearLast() {
        Quarter temp = Quarter.parseQuarter("Q2 2023");
        arrange(2, 2023);
        assertEquals(temp, quarter);
    }
}