

/* First created by JCasGen Fri Nov 14 17:06:50 CST 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Nov 14 17:06:50 CST 2014
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/3/leoTypeDescription_f4ab3cd8-8900-4294-a815-2c62885add957243376802218761608.xml
 * @generated */
public class PotentialBp extends Pattern {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(PotentialBp.class);
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
  protected PotentialBp() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public PotentialBp(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public PotentialBp(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public PotentialBp(JCas jcas, int begin, int end) {
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
    if (PotentialBp_Type.featOkTst && ((PotentialBp_Type)jcasType).casFeat_value1 == null)
      jcasType.jcas.throwFeatMissing("value1", "gov.va.vinci.vitals.types.PotentialBp");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((PotentialBp_Type)jcasType).casFeatCode_value1)));}
    
  /** setter for value1 - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue1(Annotation v) {
    if (PotentialBp_Type.featOkTst && ((PotentialBp_Type)jcasType).casFeat_value1 == null)
      jcasType.jcas.throwFeatMissing("value1", "gov.va.vinci.vitals.types.PotentialBp");
    jcasType.ll_cas.ll_setRefValue(addr, ((PotentialBp_Type)jcasType).casFeatCode_value1, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: value2

  /** getter for value2 - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getValue2() {
    if (PotentialBp_Type.featOkTst && ((PotentialBp_Type)jcasType).casFeat_value2 == null)
      jcasType.jcas.throwFeatMissing("value2", "gov.va.vinci.vitals.types.PotentialBp");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((PotentialBp_Type)jcasType).casFeatCode_value2)));}
    
  /** setter for value2 - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue2(Annotation v) {
    if (PotentialBp_Type.featOkTst && ((PotentialBp_Type)jcasType).casFeat_value2 == null)
      jcasType.jcas.throwFeatMissing("value2", "gov.va.vinci.vitals.types.PotentialBp");
    jcasType.ll_cas.ll_setRefValue(addr, ((PotentialBp_Type)jcasType).casFeatCode_value2, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    