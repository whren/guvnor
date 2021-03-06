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
package org.drools.guvnor.client.widgets.drools.decoratedgrid;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.drools.guvnor.client.configurations.ApplicationPreferences;
import org.drools.guvnor.client.decisiontable.widget.DTCellValueUtilities;
import org.drools.guvnor.client.decisiontable.widget.DecisionTableCellValueFactory;
import org.drools.guvnor.server.util.JVMDateConverter;
import org.drools.ide.common.client.modeldriven.ModelField;
import org.drools.ide.common.client.modeldriven.ModelField.FIELD_CLASS_TYPE;
import org.drools.ide.common.client.modeldriven.SuggestionCompletionEngine;
import org.drools.ide.common.client.modeldriven.brl.BaseSingleFieldConstraint;
import org.drools.ide.common.client.modeldriven.dt52.ActionInsertFactCol52;
import org.drools.ide.common.client.modeldriven.dt52.ActionSetFieldCol52;
import org.drools.ide.common.client.modeldriven.dt52.AttributeCol52;
import org.drools.ide.common.client.modeldriven.dt52.ConditionCol52;
import org.drools.ide.common.client.modeldriven.dt52.DTCellValue52;
import org.drools.ide.common.client.modeldriven.dt52.DTDataTypes52;
import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;
import org.drools.ide.common.client.modeldriven.dt52.Pattern52;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for CellValueFactory
 */
public class CellValueFactoryTests {

    private SuggestionCompletionEngine    sce     = null;
    private GuidedDecisionTable52         dt      = null;
    private DecisionTableCellValueFactory factory = null;

    private AttributeCol52                at1     = null;
    private AttributeCol52                at2     = null;
    private ConditionCol52                c1      = null;
    private ConditionCol52                c2      = null;
    private ConditionCol52                c3      = null;
    private ConditionCol52                c4      = null;
    private ConditionCol52                c5      = null;
    private ConditionCol52                c6      = null;
    private ConditionCol52                c7      = null;
    private ConditionCol52                c8      = null;
    private ConditionCol52                c9      = null;
    private ConditionCol52                c10     = null;
    private ConditionCol52                c11     = null;
    private ActionSetFieldCol52           a1      = null;
    private ActionInsertFactCol52         a2      = null;

    @Before
    @SuppressWarnings("serial")
    public void setup() {
        sce = new SuggestionCompletionEngine();

        sce.setFieldsForTypes( new HashMap<String, ModelField[]>() {
            {
                put( "MyClass",
                        new ModelField[]{
                                new ModelField( "bigDecimalField",
                                                Integer.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_NUMERIC_BIGDECIMAL ),
                                new ModelField( "bigIntegerField",
                                                Integer.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_NUMERIC_BIGINTEGER ),
                                new ModelField( "byteField",
                                                Integer.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_NUMERIC_BYTE ),
                                new ModelField( "doubleField",
                                                Integer.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_NUMERIC_DOUBLE ),
                                new ModelField( "floatField",
                                                Integer.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_NUMERIC_FLOAT ),
                                new ModelField( "integerField",
                                                Integer.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_NUMERIC_INTEGER ),
                                new ModelField( "longField",
                                                Integer.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_NUMERIC_LONG ),
                                new ModelField( "shortField",
                                                Integer.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_NUMERIC_SHORT ),
                                new ModelField( "stringField",
                                                String.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_STRING ),
                                new ModelField( "dateField",
                                                Boolean.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_DATE ),
                                new ModelField( "booleanField",
                                                Boolean.class.getName(),
                                                FIELD_CLASS_TYPE.REGULAR_CLASS,
                                                SuggestionCompletionEngine.TYPE_BOOLEAN )
                        } );
            }
        } );

        dt = new GuidedDecisionTable52();

        at1 = new AttributeCol52();
        at1.setAttribute( "salience" );
        at2 = new AttributeCol52();
        at2.setAttribute( "enabled" );

        dt.getAttributeCols().add( at1 );
        dt.getAttributeCols().add( at2 );

        Pattern52 p1 = new Pattern52();
        p1.setBoundName( "c1" );
        p1.setFactType( "MyClass" );

        c1 = new ConditionCol52();
        c1.setFactField( "stringField" );
        c1.setOperator( "==" );
        c1.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p1.getChildColumns().add( c1 );
        dt.getConditions().add( p1 );

        Pattern52 p2 = new Pattern52();
        p2.setBoundName( "c2" );
        p2.setFactType( "MyClass" );

        c2 = new ConditionCol52();
        c2.setFactField( "bigDecimalField" );
        c2.setOperator( "==" );
        c2.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p2.getChildColumns().add( c2 );
        dt.getConditions().add( p2 );

        c3 = new ConditionCol52();
        c3.setFactField( "bigIntegerField" );
        c3.setOperator( "==" );
        c3.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p2.getChildColumns().add( c3 );

        c4 = new ConditionCol52();
        c4.setFactField( "byteField" );
        c4.setOperator( "==" );
        c4.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p2.getChildColumns().add( c4 );

        c5 = new ConditionCol52();
        c5.setFactField( "doubleField" );
        c5.setOperator( "==" );
        c5.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p2.getChildColumns().add( c5 );

        c6 = new ConditionCol52();
        c6.setFactField( "floatField" );
        c6.setOperator( "==" );
        c6.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p2.getChildColumns().add( c6 );

        c7 = new ConditionCol52();
        c7.setFactField( "integerField" );
        c7.setOperator( "==" );
        c7.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p2.getChildColumns().add( c7 );

        c8 = new ConditionCol52();
        c8.setFactField( "longField" );
        c8.setOperator( "==" );
        c8.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p2.getChildColumns().add( c8 );

        c9 = new ConditionCol52();
        c9.setFactField( "shortField" );
        c9.setOperator( "==" );
        c9.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p2.getChildColumns().add( c9 );

        Pattern52 p3 = new Pattern52();
        p3.setBoundName( "c3" );
        p3.setFactType( "MyClass" );

        c10 = new ConditionCol52();
        c10.setFactField( "dateField" );
        c10.setOperator( "==" );
        c10.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p3.getChildColumns().add( c10 );
        dt.getConditions().add( p3 );

        Pattern52 p4 = new Pattern52();
        p4.setBoundName( "c4" );
        p4.setFactType( "MyClass" );

        c11 = new ConditionCol52();
        c11.setFactField( "booleanField" );
        c11.setOperator( "==" );
        c11.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        p4.getChildColumns().add( c11 );
        dt.getConditions().add( p4 );

        a1 = new ActionSetFieldCol52();
        a1.setBoundName( "c1" );
        a1.setFactField( "stringField" );
        dt.getActionCols().add( a1 );

        a2 = new ActionInsertFactCol52();
        a2.setBoundName( "a2" );
        a2.setFactType( "MyClass" );
        a2.setFactField( "stringField" );
        dt.getActionCols().add( a2 );

        factory = new DecisionTableCellValueFactory( dt,
                                                     sce );

        Map<String, String> preferences = new HashMap<String, String>();
        preferences.put( ApplicationPreferences.DATE_FORMAT,
                         "dd-MMM-yyyy" );
        ApplicationPreferences.setUp( preferences );

        DTCellValueUtilities.injectDateConvertor( JVMDateConverter.getInstance() );

    }

    @Test
    public void testDataTypes() {

        Calendar cdob = Calendar.getInstance();
        cdob.clear();
        cdob.set( 2000,
                  0,
                  1 );
        Date dob = cdob.getTime();

        DTCellValue52 dcv1 = new DTCellValue52( Boolean.TRUE );
        DTCellValue52 dcv2 = new DTCellValue52( dob );
        DTCellValue52 dcv3 = new DTCellValue52( new BigDecimal( 1 ) );
        DTCellValue52 dcv4 = new DTCellValue52( new BigInteger( "1" ) );
        DTCellValue52 dcv5 = new DTCellValue52( new Byte( "1" ) );
        DTCellValue52 dcv6 = new DTCellValue52( 1.0d );
        DTCellValue52 dcv7 = new DTCellValue52( 1.0f );
        DTCellValue52 dcv8 = new DTCellValue52( new Integer( 1 ) );
        DTCellValue52 dcv9 = new DTCellValue52( 1l );
        DTCellValue52 dcv10 = new DTCellValue52( new Short( "1" ) );
        DTCellValue52 dcv11 = new DTCellValue52( "Smurf" );

        assertEquals( dcv1.getDataType(),
                      DTDataTypes52.BOOLEAN );
        assertEquals( dcv2.getDataType(),
                      DTDataTypes52.DATE );
        assertEquals( dcv3.getDataType(),
                      DTDataTypes52.NUMERIC_BIGDECIMAL );
        assertEquals( dcv4.getDataType(),
                      DTDataTypes52.NUMERIC_BIGINTEGER );
        assertEquals( dcv5.getDataType(),
                      DTDataTypes52.NUMERIC_BYTE );
        assertEquals( dcv6.getDataType(),
                      DTDataTypes52.NUMERIC_DOUBLE );
        assertEquals( dcv7.getDataType(),
                      DTDataTypes52.NUMERIC_FLOAT );
        assertEquals( dcv8.getDataType(),
                      DTDataTypes52.NUMERIC_INTEGER );
        assertEquals( dcv9.getDataType(),
                      DTDataTypes52.NUMERIC_LONG );
        assertEquals( dcv10.getDataType(),
                      DTDataTypes52.NUMERIC_SHORT );
        assertEquals( dcv11.getDataType(),
                      DTDataTypes52.STRING );
    }

    @Test
    public void testEmptyCells() {

        CellValue< ? extends Comparable< ? >> cell1 = factory.convertModelCellValue( at1,
                                                                                     new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell2 = factory.convertModelCellValue( at2,
                                                                                     new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell3 = factory.convertModelCellValue( c1,
                                                                                     new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell4 = factory.convertModelCellValue( c2,
                                                                                     new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell5 = factory.convertModelCellValue( c3,
                                                                                     new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell6 = factory.convertModelCellValue( c4,
                                                                                     new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell7 = factory.convertModelCellValue( c5,
                                                                                     new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell8 = factory.convertModelCellValue( c6,
                                                                                     new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell9 = factory.convertModelCellValue( c7,
                                                                                     new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell10 = factory.convertModelCellValue( c8,
                                                                                      new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell11 = factory.convertModelCellValue( c9,
                                                                                      new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell12 = factory.convertModelCellValue( c10,
                                                                                      new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell13 = factory.convertModelCellValue( c11,
                                                                                      new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell14 = factory.convertModelCellValue( a1,
                                                                                      new DTCellValue52() );
        CellValue< ? extends Comparable< ? >> cell15 = factory.convertModelCellValue( a2,
                                                                                      new DTCellValue52() );

        assertEquals( cell1.getValue(),
                      null );
        assertEquals( cell2.getValue(),
                      Boolean.FALSE );
        assertEquals( cell3.getValue(),
                      null );
        assertEquals( cell4.getValue(),
                      null );
        assertEquals( cell5.getValue(),
                      null );
        assertEquals( cell6.getValue(),
                      null );
        assertEquals( cell7.getValue(),
                      null );
        assertEquals( cell8.getValue(),
                      null );
        assertEquals( cell9.getValue(),
                      null );
        assertEquals( cell10.getValue(),
                      null );
        assertEquals( cell11.getValue(),
                      null );
        assertEquals( cell12.getValue(),
                      null );
        assertEquals( cell13.getValue(),
                      Boolean.FALSE );
        assertEquals( cell14.getValue(),
                      null );
        assertEquals( cell15.getValue(),
                      null );
    }

    @Test
    public void testTypedValues() {

        Calendar cdob = Calendar.getInstance();
        cdob.clear();
        cdob.set( 2000,
                  0,
                  1 );
        Date dob = cdob.getTime();

        DTCellValue52 dcv1 = new DTCellValue52( new Integer( 1 ) );
        DTCellValue52 dcv2 = new DTCellValue52( Boolean.TRUE );
        DTCellValue52 dcv3 = new DTCellValue52( "Michael" );
        DTCellValue52 dcv4 = new DTCellValue52( new BigDecimal( 11 ) );
        DTCellValue52 dcv5 = new DTCellValue52( new BigInteger( "11" ) );
        DTCellValue52 dcv6 = new DTCellValue52( new Byte( "11" ) );
        DTCellValue52 dcv7 = new DTCellValue52( 11.0d );
        DTCellValue52 dcv8 = new DTCellValue52( 11.0f );
        DTCellValue52 dcv9 = new DTCellValue52( new Integer( 11 ) );
        DTCellValue52 dcv10 = new DTCellValue52( 11l );
        DTCellValue52 dcv11 = new DTCellValue52( new Short( "11" ) );
        DTCellValue52 dcv12 = new DTCellValue52( dob );
        DTCellValue52 dcv13 = new DTCellValue52( Boolean.TRUE );
        DTCellValue52 dcv14 = new DTCellValue52( "Mike" );
        DTCellValue52 dcv15 = new DTCellValue52( "Mike" );

        CellValue< ? extends Comparable< ? >> cell1 = factory.convertModelCellValue( at1,
                                                                                     dcv1 );
        CellValue< ? extends Comparable< ? >> cell2 = factory.convertModelCellValue( at2,
                                                                                     dcv2 );
        CellValue< ? extends Comparable< ? >> cell3 = factory.convertModelCellValue( c1,
                                                                                     dcv3 );
        CellValue< ? extends Comparable< ? >> cell4 = factory.convertModelCellValue( c2,
                                                                                     dcv4 );
        CellValue< ? extends Comparable< ? >> cell5 = factory.convertModelCellValue( c3,
                                                                                     dcv5 );
        CellValue< ? extends Comparable< ? >> cell6 = factory.convertModelCellValue( c4,
                                                                                     dcv6 );
        CellValue< ? extends Comparable< ? >> cell7 = factory.convertModelCellValue( c5,
                                                                                     dcv7 );
        CellValue< ? extends Comparable< ? >> cell8 = factory.convertModelCellValue( c6,
                                                                                     dcv8 );
        CellValue< ? extends Comparable< ? >> cell9 = factory.convertModelCellValue( c7,
                                                                                     dcv9 );
        CellValue< ? extends Comparable< ? >> cell10 = factory.convertModelCellValue( c8,
                                                                                      dcv10 );
        CellValue< ? extends Comparable< ? >> cell11 = factory.convertModelCellValue( c9,
                                                                                      dcv11 );
        CellValue< ? extends Comparable< ? >> cell12 = factory.convertModelCellValue( c10,
                                                                                      dcv12 );
        CellValue< ? extends Comparable< ? >> cell13 = factory.convertModelCellValue( c11,
                                                                                      dcv13 );
        CellValue< ? extends Comparable< ? >> cell14 = factory.convertModelCellValue( a1,
                                                                                      dcv14 );
        CellValue< ? extends Comparable< ? >> cell15 = factory.convertModelCellValue( a2,
                                                                                      dcv15 );

        assertEquals( cell1.getValue(),
                      new Integer( 1 ) );
        assertEquals( cell2.getValue(),
                      Boolean.TRUE );
        assertEquals( cell3.getValue(),
                      "Michael" );
        assertEquals( cell4.getValue(),
                      new BigDecimal( 11 ) );
        assertEquals( cell5.getValue(),
                      new BigInteger( "11" ) );
        assertEquals( cell6.getValue(),
                      new Byte( "11" ) );
        assertEquals( cell7.getValue(),
                      11.0d );
        assertEquals( cell8.getValue(),
                      11.0f );
        assertEquals( cell9.getValue(),
                      new Integer( 11 ) );
        assertEquals( cell10.getValue(),
                      11l );
        assertEquals( cell11.getValue(),
                      new Short( "11" ) );
        assertEquals( cell12.getValue(),
                      dob );
        assertEquals( cell13.getValue(),
                      Boolean.TRUE );
        assertEquals( cell14.getValue(),
                      "Mike" );
        assertEquals( cell15.getValue(),
                      "Mike" );
    }

    @Test
    public void testStringValues() {

        Calendar cdob = Calendar.getInstance();
        cdob.clear();
        cdob.set( 2000,
                  0,
                  1 );
        Date dob = cdob.getTime();

        DTCellValue52 dcv1 = new DTCellValue52( "1" );
        DTCellValue52 dcv2 = new DTCellValue52( "true" );
        DTCellValue52 dcv3 = new DTCellValue52( "Michael" );
        DTCellValue52 dcv4 = new DTCellValue52( "11" );
        DTCellValue52 dcv5 = new DTCellValue52( "11" );
        DTCellValue52 dcv6 = new DTCellValue52( "11" );
        DTCellValue52 dcv7 = new DTCellValue52( "11" );
        DTCellValue52 dcv8 = new DTCellValue52( "11" );
        DTCellValue52 dcv9 = new DTCellValue52( "11" );
        DTCellValue52 dcv10 = new DTCellValue52( "11" );
        DTCellValue52 dcv11 = new DTCellValue52( "11" );
        DTCellValue52 dcv12 = new DTCellValue52( "01-JAN-2000" );
        DTCellValue52 dcv13 = new DTCellValue52( "true" );
        DTCellValue52 dcv14 = new DTCellValue52( "Mike" );
        DTCellValue52 dcv15 = new DTCellValue52( "Mike" );

        CellValue< ? extends Comparable< ? >> cell1 = factory.convertModelCellValue( at1,
                                                                                     dcv1 );
        CellValue< ? extends Comparable< ? >> cell2 = factory.convertModelCellValue( at2,
                                                                                     dcv2 );
        CellValue< ? extends Comparable< ? >> cell3 = factory.convertModelCellValue( c1,
                                                                                     dcv3 );
        CellValue< ? extends Comparable< ? >> cell4 = factory.convertModelCellValue( c2,
                                                                                     dcv4 );
        CellValue< ? extends Comparable< ? >> cell5 = factory.convertModelCellValue( c3,
                                                                                     dcv5 );
        CellValue< ? extends Comparable< ? >> cell6 = factory.convertModelCellValue( c4,
                                                                                     dcv6 );
        CellValue< ? extends Comparable< ? >> cell7 = factory.convertModelCellValue( c5,
                                                                                     dcv7 );
        CellValue< ? extends Comparable< ? >> cell8 = factory.convertModelCellValue( c6,
                                                                                     dcv8 );
        CellValue< ? extends Comparable< ? >> cell9 = factory.convertModelCellValue( c7,
                                                                                     dcv9 );
        CellValue< ? extends Comparable< ? >> cell10 = factory.convertModelCellValue( c8,
                                                                                      dcv10 );
        CellValue< ? extends Comparable< ? >> cell11 = factory.convertModelCellValue( c9,
                                                                                      dcv11 );
        CellValue< ? extends Comparable< ? >> cell12 = factory.convertModelCellValue( c10,
                                                                                      dcv12 );
        CellValue< ? extends Comparable< ? >> cell13 = factory.convertModelCellValue( c11,
                                                                                      dcv13 );
        CellValue< ? extends Comparable< ? >> cell14 = factory.convertModelCellValue( a1,
                                                                                      dcv14 );
        CellValue< ? extends Comparable< ? >> cell15 = factory.convertModelCellValue( a2,
                                                                                      dcv15 );

        assertEquals( cell1.getValue(),
                      new Integer( 1 ) );
        assertEquals( cell2.getValue(),
                      Boolean.TRUE );
        assertEquals( cell3.getValue(),
                      "Michael" );
        assertEquals( cell4.getValue(),
                      new BigDecimal( 11 ) );
        assertEquals( cell5.getValue(),
                      new BigInteger( "11" ) );
        assertEquals( cell6.getValue(),
                      new Byte( "11" ) );
        assertEquals( cell7.getValue(),
                      11.0d );
        assertEquals( cell8.getValue(),
                      11.0f );
        assertEquals( cell9.getValue(),
                      new Integer( 11 ) );
        assertEquals( cell10.getValue(),
                      11l );
        assertEquals( cell11.getValue(),
                      new Short( "11" ) );
        assertEquals( cell12.getValue(),
                      dob );
        assertEquals( cell13.getValue(),
                      Boolean.TRUE );
        assertEquals( cell14.getValue(),
                      "Mike" );
        assertEquals( cell15.getValue(),
                      "Mike" );
    }

    @Test
    public void testConversionEmptyValues() {

        DTCellValue52 dcv1 = new DTCellValue52( "" );
        DTCellValue52 dcv2 = new DTCellValue52( "" );
        DTCellValue52 dcv3 = new DTCellValue52( "" );
        DTCellValue52 dcv4 = new DTCellValue52( "" );
        DTCellValue52 dcv5 = new DTCellValue52( "" );
        DTCellValue52 dcv6 = new DTCellValue52( "" );
        DTCellValue52 dcv7 = new DTCellValue52( "" );
        DTCellValue52 dcv8 = new DTCellValue52( "" );
        DTCellValue52 dcv9 = new DTCellValue52( "" );
        DTCellValue52 dcv10 = new DTCellValue52( "" );
        DTCellValue52 dcv11 = new DTCellValue52( "" );
        DTCellValue52 dcv12 = new DTCellValue52( "" );
        DTCellValue52 dcv13 = new DTCellValue52( "" );
        DTCellValue52 dcv14 = new DTCellValue52( "" );
        DTCellValue52 dcv15 = new DTCellValue52( "" );

        CellValue< ? extends Comparable< ? >> cell1 = factory.convertModelCellValue( at1,
                                                                                     dcv1 );
        CellValue< ? extends Comparable< ? >> cell2 = factory.convertModelCellValue( at2,
                                                                                     dcv2 );
        CellValue< ? extends Comparable< ? >> cell3 = factory.convertModelCellValue( c1,
                                                                                     dcv3 );
        CellValue< ? extends Comparable< ? >> cell4 = factory.convertModelCellValue( c2,
                                                                                     dcv4 );
        CellValue< ? extends Comparable< ? >> cell5 = factory.convertModelCellValue( c3,
                                                                                     dcv5 );
        CellValue< ? extends Comparable< ? >> cell6 = factory.convertModelCellValue( c4,
                                                                                     dcv6 );
        CellValue< ? extends Comparable< ? >> cell7 = factory.convertModelCellValue( c5,
                                                                                     dcv7 );
        CellValue< ? extends Comparable< ? >> cell8 = factory.convertModelCellValue( c6,
                                                                                     dcv8 );
        CellValue< ? extends Comparable< ? >> cell9 = factory.convertModelCellValue( c7,
                                                                                     dcv9 );
        CellValue< ? extends Comparable< ? >> cell10 = factory.convertModelCellValue( c8,
                                                                                      dcv10 );
        CellValue< ? extends Comparable< ? >> cell11 = factory.convertModelCellValue( c9,
                                                                                      dcv11 );
        CellValue< ? extends Comparable< ? >> cell12 = factory.convertModelCellValue( c10,
                                                                                      dcv12 );
        CellValue< ? extends Comparable< ? >> cell13 = factory.convertModelCellValue( c11,
                                                                                      dcv13 );
        CellValue< ? extends Comparable< ? >> cell14 = factory.convertModelCellValue( a1,
                                                                                      dcv14 );
        CellValue< ? extends Comparable< ? >> cell15 = factory.convertModelCellValue( a2,
                                                                                      dcv15 );

        assertEquals( cell1.getValue(),
                      null );
        assertEquals( cell2.getValue(),
                      Boolean.FALSE );
        assertEquals( cell3.getValue(),
                      null );
        assertEquals( cell4.getValue(),
                      null );
        assertEquals( cell5.getValue(),
                      null );
        assertEquals( cell6.getValue(),
                      null );
        assertEquals( cell7.getValue(),
                      null );
        assertEquals( cell8.getValue(),
                      null );
        assertEquals( cell9.getValue(),
                      null );
        assertEquals( cell10.getValue(),
                      null );
        assertEquals( cell11.getValue(),
                      null );
        assertEquals( cell12.getValue(),
                      null );
        assertEquals( cell13.getValue(),
                      Boolean.FALSE );
        assertEquals( cell14.getValue(),
                      null );
        assertEquals( cell15.getValue(),
                      null );

        assertEquals( dcv1.getDataType(),
                      DTDataTypes52.NUMERIC_INTEGER );
        assertEquals( dcv2.getDataType(),
                      DTDataTypes52.BOOLEAN );
        assertEquals( dcv3.getDataType(),
                      DTDataTypes52.STRING );
        assertEquals( dcv4.getDataType(),
                      DTDataTypes52.NUMERIC_BIGDECIMAL );
        assertEquals( dcv5.getDataType(),
                      DTDataTypes52.NUMERIC_BIGINTEGER );
        assertEquals( dcv6.getDataType(),
                      DTDataTypes52.NUMERIC_BYTE );
        assertEquals( dcv7.getDataType(),
                      DTDataTypes52.NUMERIC_DOUBLE );
        assertEquals( dcv8.getDataType(),
                      DTDataTypes52.NUMERIC_FLOAT );
        assertEquals( dcv9.getDataType(),
                      DTDataTypes52.NUMERIC_INTEGER );
        assertEquals( dcv10.getDataType(),
                      DTDataTypes52.NUMERIC_LONG );
        assertEquals( dcv11.getDataType(),
                      DTDataTypes52.NUMERIC_SHORT );
        assertEquals( dcv12.getDataType(),
                      DTDataTypes52.DATE );
        assertEquals( dcv13.getDataType(),
                      DTDataTypes52.BOOLEAN );
        assertEquals( dcv14.getDataType(),
                      DTDataTypes52.STRING );
        assertEquals( dcv15.getDataType(),
                      DTDataTypes52.STRING );
    }

}
