/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.ide.common.modeldriven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import org.drools.ide.common.server.util.DataEnumLoader;
import org.mvel2.MVEL;
import org.mvel2.templates.TemplateRuntime;

public class DataEnumLoaderTest {

    @Test
    @Ignore
    public void testEnumGeneration() throws Exception {

        Object result = MVEL.eval("[2, 3, 4, ]", new HashMap());
        assertTrue(result instanceof List);
        List l = (List) result;
        assertEquals(3, l.size());

        result = MVEL.eval("['Person.age' : [2, 3]\n, 'Person.name' : ['qqq', \n'ccc']]", new HashMap());

        DataEnumLoader loader = new DataEnumLoader(readLines().toString());

        assertFalse(loader.getErrors().toString(), loader.hasErrors());

        Map enumeration = (Map) loader.getData();
        assertEquals(loader.getErrors().toString(), 0, loader.getErrors().size());
        assertEquals(3, enumeration.size());

        String[] list = (String[]) enumeration.get("Person.age");
        assertEquals(4, list.length);
        assertEquals("1", list[0]);
        assertEquals("2", list[1]);

        list = (String[]) enumeration.get("Person.rating");
        assertEquals(2, list.length);
        assertEquals("High", list[0]);
        assertEquals("Low", list[1]);

        loader = new DataEnumLoader("goober poo error");
        assertEquals(0, loader.getData().size());
        assertFalse(loader.getErrors().size() == 0);
        assertTrue(loader.hasErrors());

    }

    @Test
    public void testNoOp() {
        DataEnumLoader loader = new DataEnumLoader(" ");
        assertFalse(loader.hasErrors());
        assertEquals(0, loader.getData().size());

        loader = new DataEnumLoader("");
        assertFalse(loader.hasErrors());
        assertEquals(0, loader.getData().size());

    }

    @Test
    public void testLiteralHelperUtilityClass() {
        //this shows how you can load it up with a class (which should return a map of keys to List.
        DataEnumLoader loader = new DataEnumLoader("=(new org.drools.ide.common.modeldriven.SampleDataSource2()).loadData()");

        assertFalse(loader.hasErrors());

        assertEquals(1, loader.getData().size());
        String[] res = (String[]) loader.getData().get("whee");
        assertEquals(2, res.length);
        assertEquals("hey", res[0]);
        assertEquals("ho", res[1]);

    }


    @Test
    public void testNewLines() {
        String s = "yeah yeah, \nyeah \nyeah";
        assertEquals("yeah yeah,\nyeah,\nyeah", DataEnumLoader.addCommasForNewLines( s ));
    }

    @Test
    public void testLazyString() {
        //in this case we are dealing with an expression which will not be resolved at load time.
        DataEnumLoader loader = new DataEnumLoader("'Person.type[sex]' : 'something @{sex}'");
        assertFalse(loader.hasErrors());

        Map data = loader.getData();
        String[] sl = (String[]) data.get("Person.type[sex]");
        String s = sl[0];
        assertEquals("something @{sex}", s);
        Map context = new HashMap() {{ put("sex", "cool"); }};

        Object r = TemplateRuntime.eval(s, context);

        assertEquals("something cool", r);

        loader = new DataEnumLoader("'Person.type[sex, money]' : '@{sex} @{money}'");
        assertFalse(loader.hasErrors());

        sl = (String[]) loader.getData().get("Person.type[sex, money]");
        s = sl[0];
        assertEquals("@{sex} @{money}", s);

    }

    private StringBuilder readLines() throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("Some.enumeration")));
        String line = "";
        StringBuilder buf = new StringBuilder();
        while ((line = r.readLine()) != null) {
            buf.append(line); buf.append('\n');
        }
        return buf;
    }


}
