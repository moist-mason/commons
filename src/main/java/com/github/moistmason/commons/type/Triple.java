package com.github.moistmason.commons.type;

import com.github.moistmason.commons.StringUtil;
import com.github.moistmason.commons.codec.CodecBuilders;
import com.mojang.serialization.Codec;
import org.jspecify.annotations.NonNull;

import static com.github.moistmason.commons.codec.CodecProviders.provider;

/**
 * Basic implementation of an immutable triple object.
 *
 * @author moist-mason
 *
 * @param <L> The left type.
 * @param <M> The middle type.
 * @param <R> The right type.
 * @param left The left value.
 * @param middle The middle value.
 * @param right The right value.
 */
public record Triple<L, M, R>(L left, M middle, R right) {

    /**
     * Creates a codec of a triple.
     *
     * @param <L> The left type.
     * @param <M> The middle type.
     * @param <R> The right type.
     * @param leftCodec The left value's codec.
     * @param middleCodec The middle value's codec.
     * @param rightCodec The right value's codec.
     * @return The triple codec.
     */
    public static <L, M, R> Codec<Triple<L, M, R>> codec(final Codec<L> leftCodec, final Codec<M> middleCodec, final Codec<R> rightCodec) {
        return CodecBuilders.of(Triple::new,
                provider(leftCodec).field("left", Triple::left),
                provider(middleCodec).field("middle", Triple::middle),
                provider(rightCodec).field("right", Triple::right)
        );
    }

    @Override
    public @NonNull String toString() {
        final String prefix = StringUtil.spaced("Triple",
                "with Left Type:", StringUtil.typeName(left),
                "and Middle Type", StringUtil.typeName(middle),
                "and Right Type",  StringUtil.typeName(right)
        );
        final String values = StringUtil.commas(left, middle, right);
        return prefix + " -> " + StringUtil.brackets(values);
    }
}
