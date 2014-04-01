

/* First created by JCasGen Mon Mar 31 19:30:42 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Mon Mar 31 19:30:42 CDT 2014
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/2/leoTypeDescription_d87e2f92-16c1-4343-a6f5-b3ba255fe459678722988791812076.xml
 * @generated */
public class Regex extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Regex.class);
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
  protected Regex() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Regex(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Regex(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Regex(JCas jcas, int begin, int end) {
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
  //* Feature: Pattern

  /** getter for Pattern - gets 
   * @generated */
  public String getPattern() {
    if (Regex_Type.featOkTst && ((Regex_Type)jcasType).casFeat_Pattern == null)
      jcasType.jcas.throwFeatMissing("Pattern", "gov.va.vinci.vitals.types.Regex");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Regex_Type)jcasType).casFeatCode_Pattern);}
    
  /** setter for Pattern - sets  
   * @generated */
  public void setPattern(String v) {
    if (Regex_Type.featOkTst && ((Regex_Type)jcasType).casFeat_Pattern == null)
      jcasType.jcas.throwFeatMissing("Pattern", "gov.va.vinci.vitals.types.Regex");
    jcasType.ll_cas.ll_setStringValue(addr, ((Regex_Type)jcasType).casFeatCode_Pattern, v);}    
   
    
  //*--------------*
  //* Feature: Groups

  /** getter for Groups - gets 
   * @generated */
  public StringArray getGroups() {
    if (Regex_Type.featOkTst && ((Regex_Type)jcasType).casFeat_Groups == null)
      jcasType.jcas.throwFeatMissing("Groups", "gov.va.vinci.vitals.types.Regex");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Regex_Type)jcasType).casFeatCode_Groups)));}
    
  /** setter for Groups - sets  
   * @generated */
  public void setGroups(StringArray v) {
    if (Regex_Type.featOkTst && ((Regex_Type)jcasType).casFeat_Groups == null)
      jcasType.jcas.throwFeatMissing("Groups", "gov.va.vinci.vitals.types.Regex");
    jcasType.ll_cas.ll_setRefValue(addr, ((Regex_Type)jcasType).casFeatCode_Groups, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Groups - gets an indexed value - 
   * @generated */
  public String getGroups(int i) {
    if (Regex_Type.featOkTst && ((Regex_Type)jcasType).casFeat_Groups == null)
      jcasType.jcas.throwFeatMissing("Groups", "gov.va.vinci.vitals.types.Regex");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Regex_Type)jcasType).casFeatCode_Groups), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Regex_Type)jcasType).casFeatCode_Groups), i);}

  /** indexed setter for Groups - sets an indexed value - 
   * @generated */
  public void setGroups(int i, String v) { 
    if (Regex_Type.featOkTst && ((Regex_Type)jcasType).casFeat_Groups == null)
      jcasType.jcas.throwFeatMissing("Groups", "gov.va.vinci.vitals.types.Regex");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Regex_Type)jcasType).casFeatCode_Groups), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Regex_Type)jcasType).casFeatCode_Groups), i, v);}
  }

    