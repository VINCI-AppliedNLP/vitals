

/* First created by JCasGen Mon Mar 31 19:30:41 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 31 19:30:41 CDT 2014
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/2/leoTypeDescription_d87e2f92-16c1-4343-a6f5-b3ba255fe459678722988791812076.xml
 * @generated */
public class AnnotationPattern extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(AnnotationPattern.class);
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
  protected AnnotationPattern() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public AnnotationPattern(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public AnnotationPattern(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public AnnotationPattern(JCas jcas, int begin, int end) {
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
  //* Feature: anchor

  /** getter for anchor - gets 
   * @generated */
  public Annotation getAnchor() {
    if (AnnotationPattern_Type.featOkTst && ((AnnotationPattern_Type)jcasType).casFeat_anchor == null)
      jcasType.jcas.throwFeatMissing("anchor", "gov.va.vinci.vitals.types.AnnotationPattern");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationPattern_Type)jcasType).casFeatCode_anchor)));}
    
  /** setter for anchor - sets  
   * @generated */
  public void setAnchor(Annotation v) {
    if (AnnotationPattern_Type.featOkTst && ((AnnotationPattern_Type)jcasType).casFeat_anchor == null)
      jcasType.jcas.throwFeatMissing("anchor", "gov.va.vinci.vitals.types.AnnotationPattern");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationPattern_Type)jcasType).casFeatCode_anchor, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: anchorPattern

  /** getter for anchorPattern - gets 
   * @generated */
  public String getAnchorPattern() {
    if (AnnotationPattern_Type.featOkTst && ((AnnotationPattern_Type)jcasType).casFeat_anchorPattern == null)
      jcasType.jcas.throwFeatMissing("anchorPattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AnnotationPattern_Type)jcasType).casFeatCode_anchorPattern);}
    
  /** setter for anchorPattern - sets  
   * @generated */
  public void setAnchorPattern(String v) {
    if (AnnotationPattern_Type.featOkTst && ((AnnotationPattern_Type)jcasType).casFeat_anchorPattern == null)
      jcasType.jcas.throwFeatMissing("anchorPattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    jcasType.ll_cas.ll_setStringValue(addr, ((AnnotationPattern_Type)jcasType).casFeatCode_anchorPattern, v);}    
   
    
  //*--------------*
  //* Feature: target

  /** getter for target - gets 
   * @generated */
  public Annotation getTarget() {
    if (AnnotationPattern_Type.featOkTst && ((AnnotationPattern_Type)jcasType).casFeat_target == null)
      jcasType.jcas.throwFeatMissing("target", "gov.va.vinci.vitals.types.AnnotationPattern");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationPattern_Type)jcasType).casFeatCode_target)));}
    
  /** setter for target - sets  
   * @generated */
  public void setTarget(Annotation v) {
    if (AnnotationPattern_Type.featOkTst && ((AnnotationPattern_Type)jcasType).casFeat_target == null)
      jcasType.jcas.throwFeatMissing("target", "gov.va.vinci.vitals.types.AnnotationPattern");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationPattern_Type)jcasType).casFeatCode_target, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: targetPattern

  /** getter for targetPattern - gets 
   * @generated */
  public String getTargetPattern() {
    if (AnnotationPattern_Type.featOkTst && ((AnnotationPattern_Type)jcasType).casFeat_targetPattern == null)
      jcasType.jcas.throwFeatMissing("targetPattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AnnotationPattern_Type)jcasType).casFeatCode_targetPattern);}
    
  /** setter for targetPattern - sets  
   * @generated */
  public void setTargetPattern(String v) {
    if (AnnotationPattern_Type.featOkTst && ((AnnotationPattern_Type)jcasType).casFeat_targetPattern == null)
      jcasType.jcas.throwFeatMissing("targetPattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    jcasType.ll_cas.ll_setStringValue(addr, ((AnnotationPattern_Type)jcasType).casFeatCode_targetPattern, v);}    
   
    
  //*--------------*
  //* Feature: pattern

  /** getter for pattern - gets 
   * @generated */
  public String getPattern() {
    if (AnnotationPattern_Type.featOkTst && ((AnnotationPattern_Type)jcasType).casFeat_pattern == null)
      jcasType.jcas.throwFeatMissing("pattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AnnotationPattern_Type)jcasType).casFeatCode_pattern);}
    
  /** setter for pattern - sets  
   * @generated */
  public void setPattern(String v) {
    if (AnnotationPattern_Type.featOkTst && ((AnnotationPattern_Type)jcasType).casFeat_pattern == null)
      jcasType.jcas.throwFeatMissing("pattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    jcasType.ll_cas.ll_setStringValue(addr, ((AnnotationPattern_Type)jcasType).casFeatCode_pattern, v);}    
  }

    