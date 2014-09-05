

/* First created by JCasGen Fri Sep 05 10:40:56 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Sep 05 10:40:56 CDT 2014
 * XML source: C:/DOCUME~1/VH813C~1/LOCALS~1/Temp/5/leoTypeDescription_c3587452-8185-4579-9d2d-39491c25dabb2527174642899996352.xml
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
  //* Feature: Pattern

  /** getter for Pattern - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPattern() {
    if (RegularExpression_Type.featOkTst && ((RegularExpression_Type)jcasType).casFeat_Pattern == null)
      jcasType.jcas.throwFeatMissing("Pattern", "gov.va.vinci.vitals.types.RegularExpression");
    return jcasType.ll_cas.ll_getStringValue(addr, ((RegularExpression_Type)jcasType).casFeatCode_Pattern);}
    
  /** setter for Pattern - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPattern(String v) {
    if (RegularExpression_Type.featOkTst && ((RegularExpression_Type)jcasType).casFeat_Pattern == null)
      jcasType.jcas.throwFeatMissing("Pattern", "gov.va.vinci.vitals.types.RegularExpression");
    jcasType.ll_cas.ll_setStringValue(addr, ((RegularExpression_Type)jcasType).casFeatCode_Pattern, v);}    
  }

    