/*
 * Copyright 2005 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.drools.guvnor.client.asseteditor.drools;

import org.drools.guvnor.client.asseteditor.AssetAttachmentFileWidget;
import org.drools.guvnor.client.asseteditor.PropertiesHolder;
import org.drools.guvnor.client.asseteditor.RuleViewer;
import org.drools.guvnor.client.asseteditor.SaveEventListener;
import org.drools.guvnor.client.explorer.ClientFactory;
import org.drools.guvnor.client.resources.DroolsGuvnorImages;
import org.drools.guvnor.client.rpc.Asset;
import org.drools.guvnor.client.widgets.drools.tables.PropertiesEditorSimpleTable;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Properties (key/value pairs) editor with a file attachment.
 */
public class PropertiesWidget extends AssetAttachmentFileWidget
        implements
        SaveEventListener {

    private PropertiesHolder            properties;
    private PropertiesEditorSimpleTable propertiesEditor;

    public PropertiesWidget(final Asset asset,
                            final RuleViewer viewer,
                            final ClientFactory clientFactory,
                            final EventBus eventBus) {
        super( asset,
               viewer,
               clientFactory,
               eventBus );

        if ( asset.getContent() == null ) {
            properties = new PropertiesHolder();
        } else {
            properties = (PropertiesHolder) asset.getContent();
        }

        VerticalPanel panel = new VerticalPanel();
        propertiesEditor = new PropertiesEditorSimpleTable( properties.list );
        panel.add( propertiesEditor );

        addSupplementaryWidget( panel );
    }

    public ImageResource getIcon() {
        return DroolsGuvnorImages.INSTANCE.newFileLarge();
    }

    public String getOverallStyleName() {
        return ""; // TODO: set correct style
    }

    public void onSave() {
        // Scrape changes back into the persistent model
        properties.list = propertiesEditor.getPropertyHolders();
        asset.setContent( properties );
    }

    public void onAfterSave() {
        // Do nothing
    }

}
