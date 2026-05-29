package com.github.moistmason.commons.type;

import com.github.moistmason.commons.StringUtil;
import com.github.moistmason.commons.codec.CodecBuilders;
import com.mojang.serialization.Codec;
import org.jspecify.annotations.NonNull;

import static com.github.moistmason.commons.codec.CodecProviders.provider;

/**
 * Basic implementation of an immutable pair object.
 *
 * @author moist-mason
 *
 * @param <L> The left type.
 * @param <R> The right type.
 * @param left The left value.
 * @param right The right value.
 */
public record Pair<L, R>(L left, R right)  {

    /**
     * Creates a codec of a pair.
     *
     * <p> This codec is serialized differently than the {@link com.mojang.datafixers.util.Pair}
     * built natively within DataFixerUpper. The DFU type is serialized as two key-value pairs using {@link com.mojang.serialization.codecs.PairCodec},
     * while this type stores its pair values with the hardcoded keys "left" and "right". </p>
     *
     * @param <L> The left type.
     * @param <R> The right type.
     * @param leftCodec The left value's codec.
     * @param rightCodec The right value's codec.
     * @return The pair codec.
     */
    public static <L, R> Codec<Pair<L, R>> codec(final Codec<L> leftCodec, final Codec<R> rightCodec) {
        return CodecBuilders.of(Pair::new,
                provider(leftCodec).field("left", Pair::left),
                provider(rightCodec).field("right", Pair::right)
        );
    }

    @Override
    public @NonNull String toString() {
        final String prefix = StringUtil.spaced("Pair",
                "with Left Type:", StringUtil.typeName(left),
                "and Right Type:", StringUtil.typeName(right)
        );
        return StringUtil.arrowSeparated(prefix, StringUtil.brackets(left + ": " + right));
    }
}
