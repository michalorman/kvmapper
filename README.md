# KVMapper - the key-value pairs mapper for Java

The **KVMapper** is a framework that provides serialization and deserialization Java objects to
and from key-value pairs.

If you have no luck to deal with web service that respond with XML or Json but is using key-value
pairs this little framework is for you. It simply gives you a parser for key-value pair data and
maps it to the Java objects (JavaBeans).

## Installation

## Usage

To map key-value pairs to Java objects use instance of ``KVMapper`` class:

    String input = "firstName = John\n" +
        "lastName = Doe"
    KVMapper mapper = new KVMapper(); // Creates mapper with default configuration
    User user = mapper.readObject(input, User.class);
    user.getFirstName(); // => "John"
    user.getLastName(); // => "Doe"

The ``readObject()`` method will read given input and instantiate object of given class
applying values to fields according to key-value pairs given in the input.

There are several ways to serialize object. You can use the ``writeObject()`` method,
that serializes object to specified output:

    StringBuilder output = new StringBuilder();
    mapper.writeObject(builder, user);
    builder.toString(); // => "firstName = John\nlastName = Doe"

You can serialize object directly to ``String`` using ``dump()`` method:

    mapper.dump(user); // => "firstName = John\nlastName = Doe"

There are also ``writeObjects()`` and ``dumpAll()`` methods which iterates over collection or
array of objects and serializes each of them.

The ``KVMapper`` allows to deserialize and serialize key-value pairs to and from a ``Map``:

    Map<String, String> result = mapper.map(input);
    result.get("firstName"); // => "John"
    result.get("lastName"); // => "Doe"

    mapper.dump(result); // => "firstName = John\nlastName = Doe"

    StringBuilder output = new StringBuilder();
    mapper.writeObject(builder, result);
    builder.toString(); // => "firstName = John\nlastName = Doe"

This can be used if you do not want to map key-value pairs to particular object. However in this
case all values will be of type ``String``, while mapping to object values will be converted to
type of the matching property. Also when using mapping to a ``Map`` rather than object, all key-value
pairs will be stored in the map, while mapping to an object, if there is no property for given key
the pair is ignored.

### Value conversion

Note that value conversion applies only if mapping key-value pairs to an object (using ``readObject()`` and
``writeObject()`` methods).

The **KVMapper** provides conversion of Java built-in types by default. It means that given pair:

    precision = 0.10

If mapped to given class:

    public class Parameters {
        float precision;
    }

Will be automatically converted to ``float``.

Besides of the default behavior the **KVMapper** allows to configure custom value converters. Refer
to the appropriate section below for more details.

#### Formatting date

TBD

#### Custom value converters

TBD

### Ignoring properties

If you want to remove certain properties from serialization apply ``@IgnoreProperty`` annotation on
getter method:

    public class User {
        @IgnoreProperty
        public String getPassword { password; }
    }

Applying to setter method will ignore property from deserialization. You can also apply ``@ExceptProperties``
annotation on the type level, providing the name of properties that should be ignored from both serialization
and deserialization:

    @ExceptProperties({ "password", "email" })
    public class User {

    }

This annotation will exclude given properties. You can also white list the properties that should be serialzied
or deserialized using ``@OnlyProperties`` annotation:

    @OnlyProperties({ "firstName", "lastName" })
    public class User {
        private String email, password, firstName, lastName;
        // getters and setters ...
    }

In this case properties ``email`` and ``password`` will not be included in serialization.