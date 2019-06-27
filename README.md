# String utilities

This library is simply a fluent wrapper around Apache StringUtils 
methods.

## Introduction

Apache StringUtils has a lot of methods that come very handy when
dealing with strings. The main advantage of StringUtils is that the
implementation is there, tested, a lot of people are using them. These
little utilities make coding easier. There is one thing with this class.

> StringUtils method names and ugly and not chain-able.

They do not provide a fluent readable API. This library is a simple
fluent API wrapoper around StringUtils that will make some code that
heavily use StringUtils even more readable.

## How to use

Using StringUtils you can say, for example,
`StringUtils.substringBefore("abrakadabra","ab")`. Which means the part
of the string `abrakadabra` before the first occurrence of `ab` in it.
You know because you have read the documentation.

Using Javax0::string you write 

```java
str("abrakadabra").before("ab")
``` 

A bit more readable, or not. It still depends on your taste and what you
got used to.

It gets more interesting when you want to have the string before the
last occurence of `ab`. Using StringUtils you write
`StringUtils.substringBeforeLast("abrakadabra","ab")`. A bit different
functionality and a new method. Using Javax0::string you write this as
the following:

```java
str("abrakadabra").before.last("ab")
```

or even (just to be grammatically correct)

```java
str("abrakadabra").before.the.last("ab")
```

What do you write when you want the same but you do not care the
character casing? Something like
`StringUtils.substringBeforeIgnoreCase("abrakadabra","AB")` does not
exists. On the other hand you can write

```java
str("abrakadabra").ignoreCase().before("AB")
```

and similarly
 
```java
 str("abrakadabra").ignoreCase().before.the.last("AB")
```
 
Without remembering all the different method names you can use all the
combinations replacing `before` with `after` .
 
StringUtils also has `prependIfMissing()` and
`prependIfMissingIgnoreCase()`. Using Javax0::string you can simply
write

```java
str("alma").prependIfMissing("A")
```

or
 
```java
str("alma").ignoreCase().prependIfMissing("A")
```

without remembering a new method name.

## Testing methods

StringUtils has some string testing methods, like `isEMpty()` and
`isBlank()`. With Javax0::string you can use these in a fluent way.
Instead of remembering the `isEmpty()` or other names you can write

```java
str("").is.empty()
```
 
Since the field `is` a reference to a special class that contains only
the boolean methods you can select with the editors autocomplete only
from the boolean methods. You can also write

```java
str("").is.not.empty()
```

instead of using the less readable `!str("").is.empty()`. Similarly you can
write

```java
str("").is.blank()
```
 
and
```java
str("").is.not.blank()
```

## Testing multiple strings

StringUtils has `StringUtils.isAnyBlank()` that works with an array of strings.
In Javax0::string you write

```java
strs("a","b").is.any.blank()
```

and similarl you can negate the expression writing

```java
strs("a","b").is.not.any.blank()
```

or

```java
strs("a","b").is.none.blank()
```

which are essentially the same. You can also use `empty()` as well
exactly the same way.

## Testing length

You also have extra functionality to check the length of strings. You
can write

```java
str(null).is.shorter.than(1)
str("").is.shorter.than(1)
str("A").is.shorter.than(1)
str("A").is.shorter.than("AA")
str("A").is.shorter.than(string("AA").before.last("A").toStringBuilder().append("A"))
str("AA").is.longer.than("A")
str("A").is.longer.than(1)
str("A").is.longer.than(0)
str("A").is.not.longer.than(1)
str("A").length() == 1
str("A").is.theSameLength.as("B")
```

to compare the length of a string to a number or to another string.

## Padding

You can pad similarly as `StringUtils.leftPad()`,
`StringUtils.rightPad()` and `StringUtils.center()`. You write

```java
str("").pad(3)
str("A").pad(3)
str("A").left().pad(3)
str("A").right().pad(3)
 str("A").both().pad(4)
```

## Null handling

When you have a `null` instead of a string, when you start with
`str(null)` it will cause no harm. On the output you will get what was
going in on the input unless you want something else. Because the API is
fluent you can write for any call something like

```java
str("abrakadabra").notNull().before("ab")
```

to guarantee that the result will be empty string even if the input was
`null`. On the other hand you can

```java
str("abrakadabra").forceNull().before("ab")
```

force that the result is `null` instead of empty string and even more
you can write

```java
str("abrakadabra").fforceNull().before("ab")
```

to get null not only for empty strings, but also for blank results.

```java
str("abrakadabra").forceEmpty().before("ab")
```

will get you empty string even if the result is `null` or blank.

## Chaining

When you start the string expression with the static method `str()` then
the result after calling some of the string methods is a string and with
that the call chain ends. Sometimes this is the best approach, but other
times you want to write longer call chains. In that case you have to use
the longer named static method `string()` or `strings()` in plural in
case the methods work on multiple strings.

Using `string()` or `strings()` you can write longer chains, like

```java
string("A").left().pad(2).right().pad(3).both().pad(5, '.').toString()
string("a"), string("abrakadabra").before("ka").after("br").toString()
```

Note that the result in this case is 

```java
string("abrakadabra").before("ab").is.not.empty()
```