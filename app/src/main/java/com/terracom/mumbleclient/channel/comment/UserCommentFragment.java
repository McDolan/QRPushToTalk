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
import com.terracom.jumble.model.User;
import com.terracom.jumble.util.JumbleObserver;

/**
 * Created by andrew on 03/03/14.
 */
public class UserCommentFragment extends AbstractCommentFragment {

    @Override
    public void requestComment(final IJumbleService service) throws RemoteException {
        service.registerObserver(new JumbleObserver() {
            @Override
            public void onUserStateUpdated(User user) throws RemoteException {
                if(user.getSession() == getSession() &&
                        user.getComment() != null) {
                    loadComment(user.getComment());
                    service.unregisterObserver(this);
                }
            }
        });
        service.requestComment(getSession());
    }

    @Override
    public void editComment(IJumbleService service, String comment) throws RemoteException {
        service.setUserComment(getSession(), comment);
    }

    public int getSession() {
        return getArguments().getInt("session");
    }
}
