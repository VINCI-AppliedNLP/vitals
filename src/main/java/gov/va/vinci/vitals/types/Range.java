

/* First created by JCasGen Tue Apr 07 22:29:27 MDT 2020 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Apr 07 22:29:27 MDT 2020
 * XML source: /var/folders/8t/nmg009gn1y12522l1gmzl14w0000gp/T/leoTypeDescription_e7dfae13-055a-44e2-a500-2d29f49aec42925118909459013116.xml
 * @generated */
public class Range extends Pattern {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Range.class);
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
  protected Range() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Range(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Range(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Range(JCas jcas, int begin, int end) {
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
  //* Feature: value1

  /** getter for value1 - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getValue1() {
    if (Range_Type.featOkTst && ((Range_Type)jcasType).casFeat_value1 == null)
      jcasType.jcas.throwFeatMissing("value1", "gov.va.vinci.vitals.types.Range");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Range_Type)jcasType).casFeatCode_value1)));}
    
  /** setter for value1 - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue1(Annotation v) {
    if (Range_Type.featOkTst && ((Range_Type)jcasType).casFeat_value1 == null)
      jcasType.jcas.throwFeatMissing("value1", "gov.va.vinci.vitals.types.Range");
    jcasType.ll_cas.ll_setRefValue(addr, ((Range_Type)jcasType).casFeatCode_value1, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: value2

  /** getter for value2 - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getValue2() {
    if (Range_Type.featOkTst && ((Range_Type)jcasType).casFeat_value2 == null)
      jcasType.jcas.throwFeatMissing("value2", "gov.va.vinci.vitals.types.Range");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Range_Type)jcasType).casFeatCode_value2)));}
    
  /** setter for value2 - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue2(Annotation v) {
    if (Range_Type.featOkTst && ((Range_Type)jcasType).casFeat_value2 == null)
      jcasType.jcas.throwFeatMissing("value2", "gov.va.vinci.vitals.types.Range");
    jcasType.ll_cas.ll_setRefValue(addr, ((Range_Type)jcasType).casFeatCode_value2, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    