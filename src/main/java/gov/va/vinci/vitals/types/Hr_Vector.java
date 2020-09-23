

/* First created by JCasGen Sat May 30 13:54:40 CDT 2020 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;


/** Type used to store the fearures and values
 * Updated by JCasGen Sat May 30 13:54:40 CDT 2020
 * XML source: C:/Users/VHE850~1/AppData/Local/Temp/3/leoTypeDescription_a77d34c5-1212-4303-982b-d7a6472e86994599447137304841678.xml
 * @generated */
public class Hr_Vector extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Hr_Vector.class);
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
  protected Hr_Vector() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Hr_Vector(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Hr_Vector(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Hr_Vector(JCas jcas, int begin, int end) {
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
  //* Feature: keys

  /** getter for keys - gets 
   * @generated
   * @return value of the feature 
   */
  public StringArray getKeys() {
    if (Hr_Vector_Type.featOkTst && ((Hr_Vector_Type)jcasType).casFeat_keys == null)
      jcasType.jcas.throwFeatMissing("keys", "gov.va.vinci.vitals.types.Hr_Vector");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_keys)));}
    
  /** setter for keys - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setKeys(StringArray v) {
    if (Hr_Vector_Type.featOkTst && ((Hr_Vector_Type)jcasType).casFeat_keys == null)
      jcasType.jcas.throwFeatMissing("keys", "gov.va.vinci.vitals.types.Hr_Vector");
    jcasType.ll_cas.ll_setRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_keys, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for keys - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getKeys(int i) {
    if (Hr_Vector_Type.featOkTst && ((Hr_Vector_Type)jcasType).casFeat_keys == null)
      jcasType.jcas.throwFeatMissing("keys", "gov.va.vinci.vitals.types.Hr_Vector");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_keys), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_keys), i);}

  /** indexed setter for keys - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setKeys(int i, String v) { 
    if (Hr_Vector_Type.featOkTst && ((Hr_Vector_Type)jcasType).casFeat_keys == null)
      jcasType.jcas.throwFeatMissing("keys", "gov.va.vinci.vitals.types.Hr_Vector");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_keys), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_keys), i, v);}
   
    
  //*--------------*
  //* Feature: values

  /** getter for values - gets 
   * @generated
   * @return value of the feature 
   */
  public StringArray getValues() {
    if (Hr_Vector_Type.featOkTst && ((Hr_Vector_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "gov.va.vinci.vitals.types.Hr_Vector");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_values)));}
    
  /** setter for values - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValues(StringArray v) {
    if (Hr_Vector_Type.featOkTst && ((Hr_Vector_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "gov.va.vinci.vitals.types.Hr_Vector");
    jcasType.ll_cas.ll_setRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_values, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for values - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getValues(int i) {
    if (Hr_Vector_Type.featOkTst && ((Hr_Vector_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "gov.va.vinci.vitals.types.Hr_Vector");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_values), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_values), i);}

  /** indexed setter for values - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setValues(int i, String v) { 
    if (Hr_Vector_Type.featOkTst && ((Hr_Vector_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "gov.va.vinci.vitals.types.Hr_Vector");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_values), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_values), i, v);}
   
    
  //*--------------*
  //* Feature: context

  /** getter for context - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getContext() {
    if (Hr_Vector_Type.featOkTst && ((Hr_Vector_Type)jcasType).casFeat_context == null)
      jcasType.jcas.throwFeatMissing("context", "gov.va.vinci.vitals.types.Hr_Vector");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_context)));}
    
  /** setter for context - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContext(Annotation v) {
    if (Hr_Vector_Type.featOkTst && ((Hr_Vector_Type)jcasType).casFeat_context == null)
      jcasType.jcas.throwFeatMissing("context", "gov.va.vinci.vitals.types.Hr_Vector");
    jcasType.ll_cas.ll_setRefValue(addr, ((Hr_Vector_Type)jcasType).casFeatCode_context, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    