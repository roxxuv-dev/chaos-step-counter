package counting.stepcounter.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record AnswerQuestionPayload(
        int answer
) implements CustomPacketPayload {

    public static final Type<AnswerQuestionPayload> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            "step-counter",
                            "answer_question"
                    )
            );

    public static final StreamCodec<
            RegistryFriendlyByteBuf,
            AnswerQuestionPayload
            > CODEC = StreamCodec.of(

            (buf, payload) ->
                    buf.writeInt(
                            payload.answer()
                    ),

            buf ->
                    new AnswerQuestionPayload(
                            buf.readInt()
                    )
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}