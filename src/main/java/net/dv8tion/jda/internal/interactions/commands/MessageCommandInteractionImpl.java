/*
 * Copyright 2015 Austin Keener, Michael Ritter, Florian Spieß, and the JDA contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dv8tion.jda.internal.interactions.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.commands.interactions.MessageCommandInteraction;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Optional;

public class MessageCommandInteractionImpl extends CommandInteractionImpl implements MessageCommandInteraction
{
    protected final long targetID;
    protected final Message targetMessage;

    public MessageCommandInteractionImpl(JDAImpl jda, DataObject data)
    {
        super(jda, data);
        DataObject commandData = data.getObject("data");

        this.targetID = commandData.getLong("target_id");

        Optional<Object> messageOptional = Arrays.stream(resolved.values()).findFirst();
        
        //Assigning null would break @Nonnull in #getTargetMessage, this should be a discord bug if there's no target message
        this.targetMessage = (Message) messageOptional.orElseThrow(() -> new NullPointerException("Target message does not exist"));
    }


    @Override
    public long getTargetIdLong()
    {
        return targetID;
    }

    @Override
    @Nonnull
    public Message getTargetMessage()
    {
        return targetMessage;
    }
}
