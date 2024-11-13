package com.enigma;

interface Converter<T, R> {
    R convert(T from);
}