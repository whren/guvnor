/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.bpm.console.client.navigation.settings;

import com.google.gwt.place.shared.PlaceController;
import org.jboss.bpm.console.client.navigation.processes.ProcessNavigationViewFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SettingsNavigationItemBuilderTest {

    private SettingsNavigationItemBuilder builder;
    private SettingsHeaderView settingsHeaderView;

    @Before
    public void setUp() throws Exception {
        settingsHeaderView = Mockito.mock(SettingsHeaderView.class);
        ProcessNavigationViewFactory navigationViewFactory = Mockito.mock(ProcessNavigationViewFactory.class);
        Mockito.when(
                navigationViewFactory.getSettingsHeaderView()
        ).thenReturn(
                settingsHeaderView
        );
        SettingsTreeView settingsTreeView = Mockito.mock(SettingsTreeView.class);
        Mockito.when(
                navigationViewFactory.getSettingsTreeView()
        ).thenReturn(
                settingsTreeView
        );
        PlaceController placeController = Mockito.mock(PlaceController.class);
        builder = new SettingsNavigationItemBuilder(navigationViewFactory, placeController);
    }

    @Test
    public void testAlwaysBuilds() throws Exception {
        Assert.assertTrue(builder.hasPermissionToBuild());
    }

    @Test
    public void testHeader() throws Exception {
        assertEquals(settingsHeaderView, builder.getHeader());
    }

    @Test
    public void testContent() throws Exception {
        Assert.assertTrue(builder.getContent() instanceof SettingsTree);
    }
}
