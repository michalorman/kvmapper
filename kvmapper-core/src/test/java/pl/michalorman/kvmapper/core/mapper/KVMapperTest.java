package pl.michalorman.kvmapper.core.mapper;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.exception.ValueConversionException;
import pl.michalorman.kvmapper.core.fixture.AnnotatedWithDate;
import pl.michalorman.kvmapper.core.fixture.Primitives;
import pl.michalorman.kvmapper.core.fixture.Types;
import pl.michalorman.kvmapper.core.fixture.WithDate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.testng.Assert.*;

public class KVMapperTest {

    private KVMapper mapper;

    @BeforeMethod
    public void setUp() {
        mapper = new KVMapper();
    }

    @DataProvider
    public Object[][] dataForTestOnPrimitives() {
        return new Object[][]{
                { Short.MAX_VALUE, Integer.MAX_VALUE, Long.MAX_VALUE, Float.MAX_VALUE, Double.MAX_VALUE, true, Character.MAX_VALUE },
                { Short.MIN_VALUE, Integer.MIN_VALUE, Long.MIN_VALUE, Float.MIN_VALUE, Double.MIN_VALUE, false, Character.MIN_VALUE }
        };
    }

    @DataProvider
    public Object[][] dataForTestOnTypes() {
        return new Object[][]{
                { Short.MAX_VALUE, Integer.MAX_VALUE, Long.MAX_VALUE, Float.MAX_VALUE, Double.MAX_VALUE, true, Character.MAX_VALUE, "Test" },
                { Short.MIN_VALUE, Integer.MIN_VALUE, Long.MIN_VALUE, Float.MIN_VALUE, Double.MIN_VALUE, false, Character.MIN_VALUE, "Test" }
        };
    }

    /*==========================================================================
        Test deserialization
     */

    @Test(description = "Should deserialize object from string input.", dataProvider = "dataForTestOnPrimitives")
    public void shouldReadObjectFromString(short typeShort, int typeInt, long typeLong, float typeFloat, double typeDouble,
                                           boolean typeBoolean, char typeChar) throws IOException {
        String input = "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar);
        StringBuilder builder = new StringBuilder(input);
        Primitives result = mapper.readObject(builder, Primitives.class);
        assertNotNull(result, "Should return new instance.");
        assertEquals(result.getTypeShort(), typeShort, "should set value of 'typeShort' property");
        assertEquals(result.getTypeInt(), typeInt, "should set value of 'typeInt' property");
        assertEquals(result.getTypeLong(), typeLong, "should set value of 'typeLong' property");
        assertEquals(result.getTypeFloat(), typeFloat, "should set value of 'typeFloat' property");
        assertEquals(result.getTypeDouble(), typeDouble, "should set value of 'typeDouble' property");
        assertEquals(result.isTypeBoolean(), typeBoolean, "should set value of 'typeBoolean' property");
        assertEquals(result.getTypeChar(), typeChar, "should set value of 'typeChar' property");
    }

    @Test(description = "Should deserialize object from stream input.", dataProvider = "dataForTestOnPrimitives")
    public void shouldReadObjectFromStream(short typeShort, int typeInt, long typeLong, float typeFloat, double typeDouble,
                                           boolean typeBoolean, char typeChar) throws IOException {
        String input = "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar);
        InputStream istream = new ByteArrayInputStream(input.getBytes());
        Primitives result = mapper.readObject(istream, Primitives.class);
        assertNotNull(result, "Should return new instance.");
        assertEquals(result.getTypeShort(), typeShort, "should set value of 'typeShort' property");
        assertEquals(result.getTypeInt(), typeInt, "should set value of 'typeInt' property");
        assertEquals(result.getTypeLong(), typeLong, "should set value of 'typeLong' property");
        assertEquals(result.getTypeFloat(), typeFloat, "should set value of 'typeFloat' property");
        assertEquals(result.getTypeDouble(), typeDouble, "should set value of 'typeDouble' property");
        assertEquals(result.isTypeBoolean(), typeBoolean, "should set value of 'typeBoolean' property");
        assertEquals(result.getTypeChar(), typeChar, "should set value of 'typeChar' property");
    }

    @Test(description = "Should deserialize object from string input.", dataProvider = "dataForTestOnTypes")
    public void shouldReadObjectFromString(Short typeShort, Integer typeInt, Long typeLong, Float typeFloat, Double typeDouble,
                                           Boolean typeBoolean, Character typeChar, String typeString) throws IOException {
        String input = "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar) +
                "\ntypeString=" + typeString;
        StringBuilder builder = new StringBuilder(input);
        Types result = mapper.readObject(builder, Types.class);
        assertNotNull(result, "Should return new instance.");
        assertEquals(result.getTypeShort(), typeShort, "should set value of 'typeShort' property");
        assertEquals(result.getTypeInt(), typeInt, "should set value of 'typeInt' property");
        assertEquals(result.getTypeLong(), typeLong, "should set value of 'typeLong' property");
        assertEquals(result.getTypeFloat(), typeFloat, "should set value of 'typeFloat' property");
        assertEquals(result.getTypeDouble(), typeDouble, "should set value of 'typeDouble' property");
        assertEquals(result.getTypeBoolean(), typeBoolean, "should set value of 'typeBoolean' property");
        if (typeChar.equals(Character.MIN_VALUE)) {
            assertNull(result.getTypeChar());
        } else {
            assertEquals(result.getTypeChar(), typeChar, "should set value of 'typeChar' property");
        }
        assertEquals(result.getTypeString(), typeString, "should set value of 'typeString' property");
    }

    @Test(description = "Should deserialize object from stream input.", dataProvider = "dataForTestOnTypes")
    public void shouldReadObjectFromStream(Short typeShort, Integer typeInt, Long typeLong, Float typeFloat, Double typeDouble,
                                           Boolean typeBoolean, Character typeChar, String typeString) throws IOException {
        String input = "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar) +
                "\ntypeString=" + typeString;
        InputStream istream = new ByteArrayInputStream(input.getBytes());
        Types result = mapper.readObject(istream, Types.class);
        assertNotNull(result, "Should return new instance.");
        assertEquals(result.getTypeShort(), typeShort, "should set value of 'typeShort' property");
        assertEquals(result.getTypeInt(), typeInt, "should set value of 'typeInt' property");
        assertEquals(result.getTypeLong(), typeLong, "should set value of 'typeLong' property");
        assertEquals(result.getTypeFloat(), typeFloat, "should set value of 'typeFloat' property");
        assertEquals(result.getTypeDouble(), typeDouble, "should set value of 'typeDouble' property");
        assertEquals(result.getTypeBoolean(), typeBoolean, "should set value of 'typeBoolean' property");
        if (typeChar.equals(Character.MIN_VALUE)) {
            assertNull(result.getTypeChar());
        } else {
            assertEquals(result.getTypeChar(), typeChar, "should set value of 'typeChar' property");
        }
        assertEquals(result.getTypeString(), typeString, "should set value of 'typeString' property");
    }

    @Test(description = "If date format not configured, should deserialize not formatted date.")
    public void shouldDeserializeNotFormattedDate() throws IOException {
        Date date = new Date();
        String input = "date=" + date.getTime();
        WithDate result = mapper.readObject(input, WithDate.class);
        assertNotNull(result, "Should instantiate object.");
        assertEquals(result.getDate(), date, "Should deserialize date.");
    }

    @Test(description = "If date format configured, should deserialize formatted date using configured date format.")
    public void shouldDeserializeFormattedDateUsingConfiguredDateFormat() throws IOException {
        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        String input = "date=" + df.format(date);
        mapper.getConfig().setDateFormat(df);
        WithDate result = mapper.readObject(input, WithDate.class);
        assertNotNull(result, "Should instantiate object.");
        assertEquals(df.format(result.getDate()), df.format(date), "Should deserialize date.");
    }

    @Test(description = "If date format annotation present, should deserialize formatted date using annotation value instead of configured date format.")
    public void shouldDeserializeFormattedDateUsingAnnotationDateFormat() throws IOException {
        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String input = "date=" + sdf.format(date);
        mapper.getConfig().setDateFormat(df);
        AnnotatedWithDate result = mapper.readObject(input, AnnotatedWithDate.class);
        assertNotNull(result, "Should instantiate object.");
        assertEquals(sdf.format(result.getDate()), sdf.format(date), "Should deserialize date.");
    }

    @Test(description = "Should throw ValueConversionException while attempt to deserialize value with invalid date format.",
            expectedExceptions = ValueConversionException.class)
    public void shouldThrowValueConversionExceptionIfDeserializingDateWithInvalidFormat() throws IOException {
        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String input = "date=" + sdf.format(date);
        mapper.getConfig().setDateFormat(df);
        mapper.readObject(input, WithDate.class);
    }


    /*==========================================================================
        Test serialization
     */

    @Test(description = "Should serialize primitive properties of an object and write result to an appendable.", dataProvider = "dataForTestOnPrimitives")
    public void shouldWriteObjectToAppendable(short typeShort, int typeInt, long typeLong, float typeFloat, double typeDouble,
                                              boolean typeBoolean, char typeChar) throws IOException {
        Primitives target = new Primitives(typeShort, typeInt, typeLong, typeFloat, typeDouble, typeBoolean, typeChar);
        StringBuilder builder = new StringBuilder();
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertEquals(result, "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar));
    }

    @Test(description = "Should serialize primitive properties of an object and write result to an output stream.", dataProvider = "dataForTestOnPrimitives")
    public void shouldWriteObjectToOutputStream(short typeShort, int typeInt, long typeLong, float typeFloat, double typeDouble,
                                                boolean typeBoolean, char typeChar) throws IOException {
        Primitives target = new Primitives(typeShort, typeInt, typeLong, typeFloat, typeDouble, typeBoolean, typeChar);
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        mapper.writeObject(ostream, target);
        String result = ostream.toString();
        assertEquals(result, "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar));
    }

    @Test(description = "Should serialize primitive properties of an object and write result to an appendable.", dataProvider = "dataForTestOnPrimitives")
    public void shouldDump(short typeShort, int typeInt, long typeLong, float typeFloat, double typeDouble,
                           boolean typeBoolean, char typeChar) throws IOException {
        Primitives target = new Primitives(typeShort, typeInt, typeLong, typeFloat, typeDouble, typeBoolean, typeChar);
        String result = mapper.dump(target);
        assertEquals(result, "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar));
    }

    @Test(description = "Should serialize reference type properties of an object and write result to an appendable.", dataProvider = "dataForTestOnTypes")
    public void shouldWriteObjectToAppendable(Short typeShort, Integer typeInt, Long typeLong, Float typeFloat, Double typeDouble,
                                              Boolean typeBoolean, Character typeChar, String typeString) throws IOException {
        Types target = new Types(typeShort, typeInt, typeLong, typeFloat, typeDouble, typeBoolean, typeChar, typeString);
        StringBuilder builder = new StringBuilder();
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertEquals(result, "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar) +
                "\ntypeString=" + typeString);
    }

    @Test(description = "Should serialize reference type properties of an object and write result to an output stream.", dataProvider = "dataForTestOnTypes")
    public void shouldWriteObjectTooOutputStream(Short typeShort, Integer typeInt, Long typeLong, Float typeFloat, Double typeDouble,
                                                 Boolean typeBoolean, Character typeChar, String typeString) throws IOException {
        Types target = new Types(typeShort, typeInt, typeLong, typeFloat, typeDouble, typeBoolean, typeChar, typeString);
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        mapper.writeObject(ostream, target);
        String result = ostream.toString();
        assertEquals(result, "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar) +
                "\ntypeString=" + typeString);
    }

    @Test(description = "Should serialize reference type properties of an object and write result to an appendable.", dataProvider = "dataForTestOnTypes")
    public void shouldDump(Short typeShort, Integer typeInt, Long typeLong, Float typeFloat, Double typeDouble,
                           Boolean typeBoolean, Character typeChar, String typeString) throws IOException {
        Types target = new Types(typeShort, typeInt, typeLong, typeFloat, typeDouble, typeBoolean, typeChar, typeString);
        String result = mapper.dump(target);
        assertEquals(result, "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar) +
                "\ntypeString=" + typeString);
    }

    @Test(description = "Should serialize object and write null properties as serialization result.")
    public void shouldWriteObjectWithNullProperties() throws IOException {
        Types target = new Types();
        StringBuilder builder = new StringBuilder();
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertEquals(result, "typeShort=null\ntypeInt=null\ntypeLong=null\ntypeFloat=null\ntypeDouble=null\ntypeBoolean=null\ntypeChar=null\ntypeString=null");
    }

    @Test(description = "Should serialize object and write null properties as serialization result.")
    public void shouldDumpWithNullProperties() throws IOException {
        String result = mapper.dump(new Types());
        assertEquals(result, "typeShort=null\ntypeInt=null\ntypeLong=null\ntypeFloat=null\ntypeDouble=null\ntypeBoolean=null\ntypeChar=null\ntypeString=null");
    }

    @Test(description = "Should serialize object using configured key-values and pairs separator.")
    public void shouldWriteObjectUsingConfiguredSeparators() throws IOException {
        Types target = new Types();
        StringBuilder builder = new StringBuilder();
        mapper.getConfig().setKeyValueSeparator(':');
        mapper.getConfig().setPairSeparator(',');
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertEquals(result, "typeShort:null,typeInt:null,typeLong:null,typeFloat:null,typeDouble:null,typeBoolean:null,typeChar:null,typeString:null");
    }

    @DataProvider
    public Object[][] dataForDisallowedPropertiesTest() {
        return new Object[][]{
                { new String[]{ "class", "typeShort" }, "typeInt=null\ntypeLong=null\ntypeFloat=null\ntypeDouble=null\ntypeBoolean=null\ntypeChar=null\ntypeString=null" },
                { new String[]{ "class", "typeShort", "typeInt", "typeLong", "typeFloat", "typeDouble", "typeBoolean", "typeChar" }, "typeString=null" },
                { new String[]{ "class", "typeShort", "typeInt", "typeLong", "typeFloat", "typeDouble", "typeBoolean", "typeChar", "typeString" }, "" },
        };
    }

    @Test(description = "Should serialize object excluding disallowed properties.", dataProvider = "dataForDisallowedPropertiesTest")
    public void shouldWriteObjectExcludingDisallowedProperties(String[] disallowedProperties, String expectedResult) throws IOException {
        Types target = new Types();
        StringBuilder builder = new StringBuilder();
        mapper.getConfig().setDisallowedProperties(disallowedProperties);
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertEquals(result, expectedResult);
    }

    @Test(description = "Should serialize all objects given in an array and write result to appendable.")
    public void shouldWriteObjectsFromArrayToAppendable() throws IOException {
        Object[] objects = new Object[]{ new ObjectA("valueA"), new ObjectB("valueB"), new ObjectC("valueC") };
        StringBuilder builder = new StringBuilder();
        mapper.writeObjects(builder, objects);
        String result = builder.toString();
        assertEquals(result, "propertyA=valueA\npropertyB=valueB\npropertyC=valueC");
    }

    @Test(description = "Should serialize all objects given in an array and write result to output stream.")
    public void shouldWriteObjectsFromArrayToOutputStream() throws IOException {
        Object[] objects = new Object[]{ new ObjectA("valueA"), new ObjectB("valueB"), new ObjectC("valueC") };
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        mapper.writeObjects(ostream, objects);
        String result = ostream.toString();
        assertEquals(result, "propertyA=valueA\npropertyB=valueB\npropertyC=valueC");
    }

    @Test(description = "Should serialize all objects given in an array.")
    public void shoulDumpFromArray() throws IOException {
        Object[] objects = new Object[]{ new ObjectA("valueA"), new ObjectB("valueB"), new ObjectC("valueC") };
        String result = mapper.dumpAll(objects);
        assertEquals(result, "propertyA=valueA\npropertyB=valueB\npropertyC=valueC");
    }

    @Test(description = "Should serialize all objects given in a collection and write result to appendable.")
    public void shouldWriteObjectsFromCollectionToAppendable() throws IOException {
        Collection<Object> objects = new ArrayList<Object>();
        objects.add(new ObjectC("valueC"));
        objects.add(new ObjectB("valueB"));
        objects.add(new ObjectA("valueA"));
        StringBuilder builder = new StringBuilder();
        mapper.writeObjects(builder, objects);
        String result = builder.toString();
        assertEquals(result, "propertyC=valueC\npropertyB=valueB\npropertyA=valueA");
    }

    @Test(description = "Should serialize all objects given in a collection and write result to output stream.")
    public void shouldWriteObjectsFromCollectionToOutputStream() throws IOException {
        Collection<Object> objects = new ArrayList<Object>();
        objects.add(new ObjectC("valueC"));
        objects.add(new ObjectB("valueB"));
        objects.add(new ObjectA("valueA"));
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        mapper.writeObjects(ostream, objects);
        String result = ostream.toString();
        assertEquals(result, "propertyC=valueC\npropertyB=valueB\npropertyA=valueA");
    }

    @Test(description = "Should serialize all objects given in a collection.")
    public void shouldDumpFromCollection() throws IOException {
        Collection<Object> objects = new ArrayList<Object>();
        objects.add(new ObjectC("valueC"));
        objects.add(new ObjectB("valueB"));
        objects.add(new ObjectA("valueA"));
        String result = mapper.dumpAll(objects);
        assertEquals(result, "propertyC=valueC\npropertyB=valueB\npropertyA=valueA");
    }

    @Test(description = "If date format not provided should serialize date property without formatting.")
    public void shouldSerializeDateWithoutFormatting() {
        Date date = new Date();
        String result = mapper.dump(new WithDate(date));
        assertEquals(result, "date=" + date.getTime(), "Should serialize date without formatting");
    }

    @Test(description = "If date format configured, should serialize date property using it.")
    public void shouldSerializeDateUsingConfiguredFormat() {
        Date date = new Date();
        Config config = new Config();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        config.setDateFormat(df);
        String result = new KVMapper(config).dump(new WithDate(date));
        assertEquals(result, "date=" + df.format(date), "Should serialize date with configured format");
    }

    @Test(description = "If date format specified using annotation, should serialize date property using it instead of configured.")
    public void shouldSerializeDateUsingFormatFromAnnotation() {
        Date date = new Date();
        Config config = new Config();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT); // configured, but overridden by annotation
        mapper.setConfig(config);
        String result = mapper.dump(new AnnotatedWithDate(date));
        assertEquals(result, "date=" + new SimpleDateFormat("yyyy-MM-dd").format(date), "Should serialize date with annotated format");
    }

    /*==========================================================================
        Classes used in tests
     */

    public class ObjectA {
        private String propertyA;

        public ObjectA(String propertyA) {
            this.propertyA = propertyA;
        }

        public String getPropertyA() {
            return propertyA;
        }
    }

    public class ObjectB {
        private String propertyB;

        public ObjectB(String propertyB) {
            this.propertyB = propertyB;
        }

        public String getPropertyB() {
            return propertyB;
        }
    }

    public class ObjectC {
        private String propertyC;

        public ObjectC(String propertyC) {
            this.propertyC = propertyC;
        }

        public String getPropertyC() {
            return propertyC;
        }
    }

}