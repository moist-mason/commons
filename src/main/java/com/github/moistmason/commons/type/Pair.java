package com.github.moistmason.commons.type;

import com.github.moistmason.commons.StringUtil;
import org.jspecify.annotations.NonNull;

/**
 * Basic implementation of an immutable pair object.
 *
 * @author moist-mason
 *
 * @param left The left value.
 * @param right The right value.
 * @param <L> The left type.
 * @param <R> The right type.
 */
public record Pair<L, R>(L left, R right) {

    @Override
    public @NonNull String toString() {
        final String prefix = StringUtil.spaced("Pair",
                "with Left Type:", StringUtil.typeName(left.getClass()),
                "and Right Type:", StringUtil.typeName(right.getClass())
        );

        return prefix + " -> " + StringUtil.brackets(left + ": " + right);
    }
}
