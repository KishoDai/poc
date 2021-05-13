package org.apache.flink.statefun.examples.greeter;

import org.apache.flink.statefun.sdk.Context;
import org.apache.flink.statefun.sdk.StatefulFunction;

public final class GreetFunction implements StatefulFunction {

    @Override
    public void invoke(Context context, Object input) {
        Greet.GreetRequest greetMessage = (Greet.GreetRequest) input;

        Greet.GreetResponse response = Greet.GreetResponse.newBuilder()
                .setWho(greetMessage.getWho())
                .setGreeting("Hello " + greetMessage.getWho())
                .build();

        context.send(GreetingConstants.GREETING_EGRESS_ID, response);
    }
}