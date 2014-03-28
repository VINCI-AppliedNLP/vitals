

/* First created by JCasGen Fri Mar 21 14:22:46 CDT 2014 */
package gov.va.vinci.leo.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Validation Annotations
 * Updated by JCasGen Fri Mar 21 14:22:46 CDT 2014
 * XML source: C:/DOCUME~1/VH813C~1/LOCALS~1/Temp/8/leoTypeDescription_69298067-c664-443d-8742-efeb9a65fff31026889561703071086.xml
 * @generated */
public class ValidationAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ValidationAnnotation.class);
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
  protected ValidationAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ValidationAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ValidationAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ValidationAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: ReferenceAnnotationGuid

  /** getter for ReferenceAnnotationGuid - gets The GUID of the annotation this validation annotation references.
   * @generated */
  public String getReferenceAnnotationGuid() {
    if (ValidationAnnotation_Type.featOkTst && ((ValidationAnnotation_Type)jcasType).casFeat_ReferenceAnnotationGuid == null)
      jcasType.jcas.throwFeatMissing("ReferenceAnnotationGuid", "gov.va.vinci.leo.types.ValidationAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ValidationAnnotation_Type)jcasType).casFeatCode_ReferenceAnnotationGuid);}
    
  /** setter for ReferenceAnnotationGuid - sets The GUID of the annotation this validation annotation references. 
   * @generated */
  public void setReferenceAnnotationGuid(String v) {
    if (ValidationAnnotation_Type.featOkTst && ((ValidationAnnotation_Type)jcasType).casFeat_ReferenceAnnotationGuid == null)
      jcasType.jcas.throwFeatMissing("ReferenceAnnotationGuid", "gov.va.vinci.leo.types.ValidationAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ValidationAnnotation_Type)jcasType).casFeatCode_ReferenceAnnotationGuid, v);}    
   
    
  //*--------------*
  //* Feature: ValidationValue

  /** getter for ValidationValue - gets The validation value for this annotation.
   * @generated */
  public String getValidationValue() {
    if (ValidationAnnotation_Type.featOkTst && ((ValidationAnnotation_Type)jcasType).casFeat_ValidationValue == null)
      jcasType.jcas.throwFeatMissing("ValidationValue", "gov.va.vinci.leo.types.ValidationAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ValidationAnnotation_Type)jcasType).casFeatCode_ValidationValue);}
    
  /** setter for ValidationValue - sets The validation value for this annotation. 
   * @generated */
  public void setValidationValue(String v) {
    if (ValidationAnnotation_Type.featOkTst && ((ValidationAnnotation_Type)jcasType).casFeat_ValidationValue == null)
      jcasType.jcas.throwFeatMissing("ValidationValue", "gov.va.vinci.leo.types.ValidationAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ValidationAnnotation_Type)jcasType).casFeatCode_ValidationValue, v);}    
   
    
  //*--------------*
  //* Feature: ValidationComment

  /** getter for ValidationComment - gets The validation comment (if any) for this annotation.
   * @generated */
  public String getValidationComment() {
    if (ValidationAnnotation_Type.featOkTst && ((ValidationAnnotation_Type)jcasType).casFeat_ValidationComment == null)
      jcasType.jcas.throwFeatMissing("ValidationComment", "gov.va.vinci.leo.types.ValidationAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ValidationAnnotation_Type)jcasType).casFeatCode_ValidationComment);}
    
  /** setter for ValidationComment - sets The validation comment (if any) for this annotation. 
   * @generated */
  public void setValidationComment(String v) {
    if (ValidationAnnotation_Type.featOkTst && ((ValidationAnnotation_Type)jcasType).casFeat_ValidationComment == null)
      jcasType.jcas.throwFeatMissing("ValidationComment", "gov.va.vinci.leo.types.ValidationAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ValidationAnnotation_Type)jcasType).casFeatCode_ValidationComment, v);}    
   
    
  //*--------------*
  //* Feature: CreatedBy

  /** getter for CreatedBy - gets The userId that created this annotation.
   * @generated */
  public String getCreatedBy() {
    if (ValidationAnnotation_Type.featOkTst && ((ValidationAnnotation_Type)jcasType).casFeat_CreatedBy == null)
      jcasType.jcas.throwFeatMissing("CreatedBy", "gov.va.vinci.leo.types.ValidationAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ValidationAnnotation_Type)jcasType).casFeatCode_CreatedBy);}
    
  /** setter for CreatedBy - sets The userId that created this annotation. 
   * @generated */
  public void setCreatedBy(String v) {
    if (ValidationAnnotation_Type.featOkTst && ((ValidationAnnotation_Type)jcasType).casFeat_CreatedBy == null)
      jcasType.jcas.throwFeatMissing("CreatedBy", "gov.va.vinci.leo.types.ValidationAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ValidationAnnotation_Type)jcasType).casFeatCode_CreatedBy, v);}    
  }

    