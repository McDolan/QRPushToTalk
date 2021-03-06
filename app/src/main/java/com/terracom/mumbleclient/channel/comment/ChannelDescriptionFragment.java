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

package com.terracom.mumbleclient.channel.comment;

import android.os.RemoteException;

import com.terracom.jumble.IJumbleService;
import com.terracom.jumble.model.Channel;
import com.terracom.jumble.util.JumbleObserver;

/**
 * Created by andrew on 03/03/14.
 */
public class ChannelDescriptionFragment extends AbstractCommentFragment {

    @Override
    public void requestComment(final IJumbleService service) throws RemoteException {
        service.registerObserver(new JumbleObserver() {
            @Override
            public void onChannelStateUpdated(Channel channel) throws RemoteException {
                if(channel.getId() == getChannelId() &&
                        channel.getDescription() != null) {
                    loadComment(channel.getDescription());
                    service.unregisterObserver(this);
                }
            }
        });
        service.requestChannelDescription(getChannelId());
    }

    @Override
    public void editComment(IJumbleService service, String comment) throws RemoteException {
        // TODO
    }

    private int getChannelId() {
        return getArguments().getInt("channel");
    }
}
