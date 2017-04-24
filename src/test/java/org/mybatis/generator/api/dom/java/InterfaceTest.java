/**
 *    Copyright 2006-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.api.dom.java;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.*;

public class InterfaceTest {

    @Test
    public void testConstructor() {

        Interface interfaze = new Interface("com.foo.UserInterface");
        assertNotNull(interfaze);
    }

    @Test
    public void testAddImportedType() {

        Interface interfaze = new Interface("com.foo.UserInterface");
        FullyQualifiedJavaType arrayList = FullyQualifiedJavaType.getNewArrayListInstance();
        interfaze.addImportedType(arrayList);

        assertNotNull(interfaze.getImportedTypes());
        assertEquals(interfaze.getImportedTypes().size(), 1);
        assertTrue(interfaze.getImportedTypes().contains(arrayList));
    }

    @Test
    public void testAddImportedTypes() {

        Interface interfaze = new Interface("com.foo.UserInterface");
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<FullyQualifiedJavaType>();

        FullyQualifiedJavaType arrayList = FullyQualifiedJavaType.getNewArrayListInstance();
        FullyQualifiedJavaType hashMap = FullyQualifiedJavaType.getNewHashMapInstance();

        importedTypes.add(arrayList);
        importedTypes.add(hashMap);
        interfaze.addImportedTypes(importedTypes);

        assertNotNull(interfaze.getImportedTypes());
        assertEquals(interfaze.getImportedTypes().size(), 2);
        assertTrue(interfaze.getImportedTypes().contains(arrayList));
        assertTrue(interfaze.getImportedTypes().contains(hashMap));
    }

    @Test
    public void testAddFileCommentLine() {

        Interface interfaze = new Interface("com.foo.UserInterface");
        interfaze.addFileCommentLine("test");

        assertNotNull(interfaze.getFileCommentLines());
        assertEquals(interfaze.getFileCommentLines().size(), 1);
        assertEquals(interfaze.getFileCommentLines().get(0), "test");
    }

    @Test
    public void testAddStaticImport() {

        Interface interfaze = new Interface("com.foo.UserInterface");
        interfaze.addStaticImport("com.foo.StaticUtil");

        assertNotNull(interfaze.getStaticImports());
        assertEquals(interfaze.getStaticImports().size(), 1);
        assertTrue(interfaze.getStaticImports().contains("com.foo.StaticUtil"));
    }

    @Test
    public void testAddStaticImports() {

        Interface interfaze = new Interface("com.foo.UserInterface");
        Set<String> staticImports = new HashSet<String>();
        staticImports.add("com.foo.StaticUtil1");
        staticImports.add("com.foo.StaticUtil2");
        interfaze.addStaticImports(staticImports);

        assertNotNull(interfaze.getStaticImports());
        assertEquals(interfaze.getStaticImports().size(), 2);
        assertTrue(interfaze.getStaticImports().contains("com.foo.StaticUtil1"));
        assertTrue(interfaze.getStaticImports().contains("com.foo.StaticUtil2"));
    }
    
    @Test
    public void testInterfaceFields() {
        Interface interfaze = new Interface("foo.Bar");
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        
        Field field = new Field("EMPTY_STRING", FullyQualifiedJavaType.getStringInstance());
        field.setInitializationString("\"\"");
        interfaze.addField(field);
        
        field = new Field("ONE", FullyQualifiedJavaType.getStringInstance());
        field.setInitializationString("\"one\"");
        interfaze.addField(field);
        
        String expected = "package foo;" + System.lineSeparator()
            + System.lineSeparator()
            + "public interface Bar {" + System.lineSeparator()
            + "    String EMPTY_STRING = \"\";" + System.lineSeparator()
            + "    String ONE = \"one\";" + System.lineSeparator()
            + "}";

        assertThat(interfaze.getFormattedContent(), is(expected));
    }
}
