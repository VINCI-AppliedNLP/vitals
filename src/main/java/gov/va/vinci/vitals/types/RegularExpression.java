

/* First created by JCasGen Mon Sep 29 13:09:42 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Mon Sep 29 13:09:42 CDT 2014
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/2/leoTypeDescription_fef9d867-19bb-4799-895f-7d3ca26905fe4428443317075727855.xml
 * @generated */
public class RegularExpression extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(RegularExpression.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected RegularExpression() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public RegularExpression(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public RegularExpression(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public RegularExpression(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: pattern

  /** getter for pattern - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPattern() {
    if (RegularExpression_Type.featOkTst && ((RegularExpression_Type)jcasType).casFeat_pattern == null)
      jcasType.jcas.throwFeatMissing("pattern", "gov.va.vinci.vitals.types.RegularExpression");
    return jcasType.ll_cas.ll_getStringValue(addr, ((RegularExpression_Type)jcasType).casFeatCode_pattern);}
    
  /** setter for pattern - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPattern(String v) {
    if (RegularExpression_Type.featOkTst && ((RegularExpression_Type)jcasType).casFeat_pattern == null)
      jcasType.jcas.throwFeatMissing("pattern", "gov.va.vinci.vitals.types.RegularExpression");
    jcasType.ll_cas.ll_setStringValue(addr, ((RegularExpression_Type)jcasType).casFeatCode_pattern, v);}    
   
    
  //*--------------*
  //* Feature: groups

  /** getter for groups - gets 
   * @generated
   * @return value of the feature 
   */
  public StringArray getGroups() {
    if (RegularExpression_Type.featOkTst && ((RegularExpression_Type)jcasType).casFeat_groups == null)
      jcasType.jcas.throwFeatMissing("groups", "gov.va.vinci.vitals.types.RegularExpression");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((RegularExpression_Type)jcasType).casFeatCode_groups)));}
    
  /** setter for groups - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGroups(StringArray v) {
    if (RegularExpression_Type.featOkTst && ((RegularExpression_Type)jcasType).casFeat_groups == null)
      jcasType.jcas.throwFeatMissing("groups", "gov.va.vinci.vitals.types.RegularExpression");
    jcasType.ll_cas.ll_setRefValue(addr, ((RegularExpression_Type)jcasType).casFeatCode_groups, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for groups - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getGroups(int i) {
    if (RegularExpression_Type.featOkTst && ((RegularExpression_Type)jcasType).casFeat_groups == null)
      jcasType.jcas.throwFeatMissing("groups", "gov.va.vinci.vitals.types.RegularExpression");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((RegularExpression_Type)jcasType).casFeatCode_groups), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((RegularExpression_Type)jcasType).casFeatCode_groups), i);}

  /** indexed setter for groups - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setGroups(int i, String v) { 
    if (RegularExpression_Type.featOkTst && ((RegularExpression_Type)jcasType).casFeat_groups == null)
      jcasType.jcas.throwFeatMissing("groups", "gov.va.vinci.vitals.types.RegularExpression");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((RegularExpression_Type)jcasType).casFeatCode_groups), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((RegularExpression_Type)jcasType).casFeatCode_groups), i, v);}
  }

    