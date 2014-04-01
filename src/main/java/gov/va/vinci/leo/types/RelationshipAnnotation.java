

/* First created by JCasGen Mon Mar 31 19:30:41 CDT 2014 */
package gov.va.vinci.leo.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** RelationshipAnnotation Annotation
 * Updated by JCasGen Mon Mar 31 19:30:41 CDT 2014
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/2/leoTypeDescription_d87e2f92-16c1-4343-a6f5-b3ba255fe459678722988791812076.xml
 * @generated */
public class RelationshipAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(RelationshipAnnotation.class);
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
  protected RelationshipAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public RelationshipAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public RelationshipAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public RelationshipAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: Source

  /** getter for Source - gets Source
   * @generated */
  public Annotation getSource() {
    if (RelationshipAnnotation_Type.featOkTst && ((RelationshipAnnotation_Type)jcasType).casFeat_Source == null)
      jcasType.jcas.throwFeatMissing("Source", "gov.va.vinci.leo.types.RelationshipAnnotation");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((RelationshipAnnotation_Type)jcasType).casFeatCode_Source)));}
    
  /** setter for Source - sets Source 
   * @generated */
  public void setSource(Annotation v) {
    if (RelationshipAnnotation_Type.featOkTst && ((RelationshipAnnotation_Type)jcasType).casFeat_Source == null)
      jcasType.jcas.throwFeatMissing("Source", "gov.va.vinci.leo.types.RelationshipAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((RelationshipAnnotation_Type)jcasType).casFeatCode_Source, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: Target

  /** getter for Target - gets Target
   * @generated */
  public FSArray getTarget() {
    if (RelationshipAnnotation_Type.featOkTst && ((RelationshipAnnotation_Type)jcasType).casFeat_Target == null)
      jcasType.jcas.throwFeatMissing("Target", "gov.va.vinci.leo.types.RelationshipAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((RelationshipAnnotation_Type)jcasType).casFeatCode_Target)));}
    
  /** setter for Target - sets Target 
   * @generated */
  public void setTarget(FSArray v) {
    if (RelationshipAnnotation_Type.featOkTst && ((RelationshipAnnotation_Type)jcasType).casFeat_Target == null)
      jcasType.jcas.throwFeatMissing("Target", "gov.va.vinci.leo.types.RelationshipAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((RelationshipAnnotation_Type)jcasType).casFeatCode_Target, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Target - gets an indexed value - Target
   * @generated */
  public Annotation getTarget(int i) {
    if (RelationshipAnnotation_Type.featOkTst && ((RelationshipAnnotation_Type)jcasType).casFeat_Target == null)
      jcasType.jcas.throwFeatMissing("Target", "gov.va.vinci.leo.types.RelationshipAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((RelationshipAnnotation_Type)jcasType).casFeatCode_Target), i);
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((RelationshipAnnotation_Type)jcasType).casFeatCode_Target), i)));}

  /** indexed setter for Target - sets an indexed value - Target
   * @generated */
  public void setTarget(int i, Annotation v) { 
    if (RelationshipAnnotation_Type.featOkTst && ((RelationshipAnnotation_Type)jcasType).casFeat_Target == null)
      jcasType.jcas.throwFeatMissing("Target", "gov.va.vinci.leo.types.RelationshipAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((RelationshipAnnotation_Type)jcasType).casFeatCode_Target), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((RelationshipAnnotation_Type)jcasType).casFeatCode_Target), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    