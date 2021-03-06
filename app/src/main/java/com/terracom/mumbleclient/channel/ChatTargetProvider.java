/*
 * Copyright (C) 2014 Andrew Comminos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.terracom.mumbleclient.channel;

import com.terracom.jumble.model.Channel;
import com.terracom.jumble.model.User;

public interface ChatTargetProvider {

    /**
     * Abstraction for user and channel chat targets.
     */
    public class ChatTarget {
        private Channel mChannel;
        private User mUser;

        public ChatTarget(Channel channel) {
            mChannel = channel;
        }

        public ChatTarget(User user) {
            mUser = user;
        }

        public Channel getChannel() {
            return mChannel;
        }

        public User getUser() {
            return mUser;
        }
    }

    /**
     * Interface for classes which wish to receive chat target change calls.
     * Created by andrew on 06/08/13.
     */
    public interface OnChatTargetSelectedListener {
        public void onChatTargetSelected(ChatTarget target);
    }

    public ChatTarget getChatTarget();
    public void setChatTarget(ChatTarget target);
    public void registerChatTargetListener(OnChatTargetSelectedListener listener);
    public void unregisterChatTargetListener(OnChatTargetSelectedListener listener);
}