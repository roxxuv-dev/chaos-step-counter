package counting.stepcounter.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPayload;
import net.minecraft.resources.ResourceLocation;

public record OpenQuestionPayload(
        String question,
        String answer1,
        String answer2,
        String answer3,
        String answer4,
        int correctAnswer,
        long expireTime
) implements CustomPayload {

    public static final Type<OpenQuestionPayload> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            "step-counter",
                            "open_question"
                    )
            );

    public static final StreamCodec<
            RegistryFriendlyByteBuf,
            OpenQuestionPayload
            > CODEC =
            StreamCodec.of(
                    (buf, payload) -> {

                        buf.writeUtf(payload.question);
                        buf.writeUtf(payload.answer1);
                        buf.writeUtf(payload.answer2);
                        buf.writeUtf(payload.answer3);
                        buf.writeUtf(payload.answer4);

                        buf.writeInt(
                                payload.correctAnswer
                        );

                        buf.writeLong(
                                payload.expireTime
                        );
                    },

                    buf -> new OpenQuestionPayload(
                            buf.readUtf(),
                            buf.readUtf(),
                            buf.readUtf(),
                            buf.readUtf(),
                            buf.readUtf(),
                            buf.readInt(),
                            buf.readLong()
                    )
            );

    @Override
    public Type<? extends CustomPayload> type() {
        return TYPE;
    }
}