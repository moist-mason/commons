package com.github.moistmason.commons.codec;

import com.github.moistmason.commons.math.DoubleRange;
import com.github.moistmason.commons.math.IntRange;
import com.github.moistmason.commons.type.Dictionary;
import com.github.moistmason.commons.type.Pair;
import com.github.moistmason.commons.type.SerializableEnum;
import com.github.moistmason.commons.type.Triple;
import com.mojang.serialization.Codec;

import java.net.URL;
import java.nio.file.Path;

/**
 * Contains providers for building codec types.
 *
 * @author moist-mason
 */
public final class CodecProviders {

    /** Provides codec builders for integers. */
    public static final CodecProvider<Integer> INT = () -> Codec.INT;

    /** Provides codec builders for doubles. */
    public static final CodecProvider<Double> DOUBLE = () -> Codec.DOUBLE;

    /** Provides codec builders for longs. */
    public static final CodecProvider<Long> LONG = () -> Codec.LONG;

    /** Provides codec builders for floats. */
    public static final CodecProvider<Float> FLOAT = () -> Codec.FLOAT;

    /** Provides codec builders for strings. */
    public static final CodecProvider<String> STRING = () -> Codec.STRING;

    /** Provides codec builders for bytes. */
    public static final CodecProvider<Byte> BYTE = () -> Codec.BYTE;

    /** Provides codec builders for shorts. */
    public static final CodecProvider<Short> SHORT = () -> Codec.SHORT;

    /** Provides codec builders for booleans. */
    public static final CodecProvider<Boolean> BOOL = () -> Codec.BOOL;

    /** Provides codec builders for URLs. */
    public static final CodecProvider<URL> URL = () -> Codecs.URL;

    /** Provides codec builders for paths. */
    public static final CodecProvider<Path> PATH = () -> Codecs.PATH;

    /** Provides codec builders for {@link IntRange}s. */
    public static final CodecProvider<IntRange> INT_RANGE = () -> IntRange.CODEC;

    /** Provides codec builders for {@link DoubleRange}s. */
    public static final CodecProvider<DoubleRange> DOUBLE_RANGE = () -> DoubleRange.CODEC;

    /**
     * Provides codec builders for the given codec.
     *
     * @param <T> The codec type.
     * @param codec The codec.
     * @return The provider.
     */
    public static <T> CodecProvider<T> provider(final Codec<T> codec) {
        return () -> codec;
    }

    /**
     * Provides codec builders for {@link Pair}s.
     *
     * @param <L> The left type.
     * @param <R> The right type.
     * @param leftCodec The left type's codec.
     * @param rightCodec The right type's codec.
     * @return The provider.
     */
    public static <L, R> CodecProvider<Pair<L, R>> pair(final Codec<L> leftCodec, final Codec<R> rightCodec) {
        return () -> Pair.codec(leftCodec, rightCodec);
    }

    /**
     * Provides codec builders for {@link Triple}.
     *
     * @param <L> The left type.
     * @param <M> The middle type.
     * @param <R> The right type.
     * @param leftCodec The left type's codec.
     * @param middleCodec The middle type's codec.
     * @param rightCodec The right type's codec.
     * @return The provider.
     */
    public static <L, M, R> CodecProvider<Triple<L, M, R>> triple(final Codec<L> leftCodec, final Codec<M> middleCodec, final Codec<R> rightCodec) {
        return () -> Triple.codec(leftCodec, middleCodec, rightCodec);
    }

    /**
     * Provides codec builders for a {@link Dictionary}.
     *
     * @param <T> The dictionary type.
     * @param valueCodec The codec of the dictionary's value type.
     * @return The provider.
     */
    public static <T> CodecProvider<Dictionary<T>> dictionary(final Codec<T> valueCodec) {
        return () -> Dictionary.codec(valueCodec);
    }

    /**
     * Provides codec builders for an enum class implemented with {@link SerializableEnum}.
     *
     * @param <E> The enum class type.
     * @param enumClass The enum class.
     * @return The provider.
     */
    public static <E extends Enum<E> & SerializableEnum> CodecProvider<E> enumCodec(final Class<E> enumClass) {
        return () -> SerializableEnum.codec(enumClass);
    }
}
