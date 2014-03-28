

/* First created by JCasGen Fri Mar 21 14:22:47 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Mar 21 14:22:47 CDT 2014
 * XML source: C:/DOCUME~1/VH813C~1/LOCALS~1/Temp/8/leoTypeDescription_69298067-c664-443d-8742-efeb9a65fff31026889561703071086.xml
 * @generated */
public class Relation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Relation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Relation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Relation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Relation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Relation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Term

  /** getter for Term - gets 
   * @generated */
  public String getTerm() {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Term == null)
      jcasType.jcas.throwFeatMissing("Term", "gov.va.vinci.vitals.types.Relation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Term);}
    
  /** setter for Term - sets  
   * @generated */
  public void setTerm(String v) {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Term == null)
      jcasType.jcas.throwFeatMissing("Term", "gov.va.vinci.vitals.types.Relation");
    jcasType.ll_cas.ll_setStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Term, v);}    
   
    
  //*--------------*
  //* Feature: Value

  /** getter for Value - gets 
   * @generated */
  public String getValue() {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Value == null)
      jcasType.jcas.throwFeatMissing("Value", "gov.va.vinci.vitals.types.Relation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Value);}
    
  /** setter for Value - sets  
   * @generated */
  public void setValue(String v) {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Value == null)
      jcasType.jcas.throwFeatMissing("Value", "gov.va.vinci.vitals.types.Relation");
    jcasType.ll_cas.ll_setStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Value, v);}    
   
    
  //*--------------*
  //* Feature: Value2

  /** getter for Value2 - gets 
   * @generated */
  public String getValue2() {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Value2 == null)
      jcasType.jcas.throwFeatMissing("Value2", "gov.va.vinci.vitals.types.Relation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Value2);}
    
  /** setter for Value2 - sets  
   * @generated */
  public void setValue2(String v) {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Value2 == null)
      jcasType.jcas.throwFeatMissing("Value2", "gov.va.vinci.vitals.types.Relation");
    jcasType.ll_cas.ll_setStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Value2, v);}    
   
    
  //*--------------*
  //* Feature: ValueString

  /** getter for ValueString - gets 
   * @generated */
  public String getValueString() {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_ValueString == null)
      jcasType.jcas.throwFeatMissing("ValueString", "gov.va.vinci.vitals.types.Relation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Relation_Type)jcasType).casFeatCode_ValueString);}
    
  /** setter for ValueString - sets  
   * @generated */
  public void setValueString(String v) {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_ValueString == null)
      jcasType.jcas.throwFeatMissing("ValueString", "gov.va.vinci.vitals.types.Relation");
    jcasType.ll_cas.ll_setStringValue(addr, ((Relation_Type)jcasType).casFeatCode_ValueString, v);}    
   
    
  //*--------------*
  //* Feature: Concept

  /** getter for Concept - gets 
   * @generated */
  public String getConcept() {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Concept == null)
      jcasType.jcas.throwFeatMissing("Concept", "gov.va.vinci.vitals.types.Relation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Concept);}
    
  /** setter for Concept - sets  
   * @generated */
  public void setConcept(String v) {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Concept == null)
      jcasType.jcas.throwFeatMissing("Concept", "gov.va.vinci.vitals.types.Relation");
    jcasType.ll_cas.ll_setStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Concept, v);}    
   
    
  //*--------------*
  //* Feature: Assessment

  /** getter for Assessment - gets 
   * @generated */
  public String getAssessment() {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Assessment == null)
      jcasType.jcas.throwFeatMissing("Assessment", "gov.va.vinci.vitals.types.Relation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Assessment);}
    
  /** setter for Assessment - sets  
   * @generated */
  public void setAssessment(String v) {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Assessment == null)
      jcasType.jcas.throwFeatMissing("Assessment", "gov.va.vinci.vitals.types.Relation");
    jcasType.ll_cas.ll_setStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Assessment, v);}    
   
    
  //*--------------*
  //* Feature: Unit

  /** getter for Unit - gets 
   * @generated */
  public String getUnit() {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Unit == null)
      jcasType.jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Relation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Unit);}
    
  /** setter for Unit - sets  
   * @generated */
  public void setUnit(String v) {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Unit == null)
      jcasType.jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Relation");
    jcasType.ll_cas.ll_setStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Unit, v);}    
   
    
  //*--------------*
  //* Feature: Range

  /** getter for Range - gets 
   * @generated */
  public String getRange() {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Range == null)
      jcasType.jcas.throwFeatMissing("Range", "gov.va.vinci.vitals.types.Relation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Range);}
    
  /** setter for Range - sets  
   * @generated */
  public void setRange(String v) {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_Range == null)
      jcasType.jcas.throwFeatMissing("Range", "gov.va.vinci.vitals.types.Relation");
    jcasType.ll_cas.ll_setStringValue(addr, ((Relation_Type)jcasType).casFeatCode_Range, v);}    
  }

    