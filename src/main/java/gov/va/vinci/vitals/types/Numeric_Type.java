
/* First created by JCasGen Sat May 30 13:54:40 CDT 2020 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import gov.va.vinci.leo.regex.types.RegularExpressionType_Type;

/** 
 * Updated by JCasGen Sat May 30 13:54:40 CDT 2020
 * @generated */
public class Numeric_Type extends RegularExpressionType_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Numeric.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.Numeric");
 
  /** @generated */
  final Feature casFeat_comment;
  /** @generated */
  final int     casFeatCode_comment;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getComment(int addr) {
        if (featOkTst && casFeat_comment == null)
      jcas.throwFeatMissing("comment", "gov.va.vinci.vitals.types.Numeric");
    return ll_cas.ll_getStringValue(addr, casFeatCode_comment);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setComment(int addr, String v) {
        if (featOkTst && casFeat_comment == null)
      jcas.throwFeatMissing("comment", "gov.va.vinci.vitals.types.Numeric");
    ll_cas.ll_setStringValue(addr, casFeatCode_comment, v);}
    
  
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "gov.va.vinci.vitals.types.Numeric");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_value);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setValue(int addr, double v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "gov.va.vinci.vitals.types.Numeric");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_decimal;
  /** @generated */
  final int     casFeatCode_decimal;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getDecimal(int addr) {
        if (featOkTst && casFeat_decimal == null)
      jcas.throwFeatMissing("decimal", "gov.va.vinci.vitals.types.Numeric");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_decimal);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDecimal(int addr, boolean v) {
        if (featOkTst && casFeat_decimal == null)
      jcas.throwFeatMissing("decimal", "gov.va.vinci.vitals.types.Numeric");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_decimal, v);}
    
  
 
  /** @generated */
  final Feature casFeat_integer;
  /** @generated */
  final int     casFeatCode_integer;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getInteger(int addr) {
        if (featOkTst && casFeat_integer == null)
      jcas.throwFeatMissing("integer", "gov.va.vinci.vitals.types.Numeric");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_integer);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setInteger(int addr, boolean v) {
        if (featOkTst && casFeat_integer == null)
      jcas.throwFeatMissing("integer", "gov.va.vinci.vitals.types.Numeric");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_integer, v);}
    
  
 
  /** @generated */
  final Feature casFeat_zero_decimal;
  /** @generated */
  final int     casFeatCode_zero_decimal;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getZero_decimal(int addr) {
        if (featOkTst && casFeat_zero_decimal == null)
      jcas.throwFeatMissing("zero_decimal", "gov.va.vinci.vitals.types.Numeric");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_zero_decimal);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setZero_decimal(int addr, boolean v) {
        if (featOkTst && casFeat_zero_decimal == null)
      jcas.throwFeatMissing("zero_decimal", "gov.va.vinci.vitals.types.Numeric");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_zero_decimal, v);}
    
  
 
  /** @generated */
  final Feature casFeat_unit;
  /** @generated */
  final int     casFeatCode_unit;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getUnit(int addr) {
        if (featOkTst && casFeat_unit == null)
      jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Numeric");
    return ll_cas.ll_getRefValue(addr, casFeatCode_unit);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setUnit(int addr, int v) {
        if (featOkTst && casFeat_unit == null)
      jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Numeric");
    ll_cas.ll_setRefValue(addr, casFeatCode_unit, v);}
    
  
 
  /** @generated */
  final Feature casFeat_source;
  /** @generated */
  final int     casFeatCode_source;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSource(int addr) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Numeric");
    return ll_cas.ll_getStringValue(addr, casFeatCode_source);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSource(int addr, String v) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Numeric");
    ll_cas.ll_setStringValue(addr, casFeatCode_source, v);}
    
  
 
  /** @generated */
  final Feature casFeat_timestamp;
  /** @generated */
  final int     casFeatCode_timestamp;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTimestamp(int addr) {
        if (featOkTst && casFeat_timestamp == null)
      jcas.throwFeatMissing("timestamp", "gov.va.vinci.vitals.types.Numeric");
    return ll_cas.ll_getRefValue(addr, casFeatCode_timestamp);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTimestamp(int addr, int v) {
        if (featOkTst && casFeat_timestamp == null)
      jcas.throwFeatMissing("timestamp", "gov.va.vinci.vitals.types.Numeric");
    ll_cas.ll_setRefValue(addr, casFeatCode_timestamp, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Numeric_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_comment = jcas.getRequiredFeatureDE(casType, "comment", "uima.cas.String", featOkTst);
    casFeatCode_comment  = (null == casFeat_comment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_comment).getCode();

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.Double", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_decimal = jcas.getRequiredFeatureDE(casType, "decimal", "uima.cas.Boolean", featOkTst);
    casFeatCode_decimal  = (null == casFeat_decimal) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_decimal).getCode();

 
    casFeat_integer = jcas.getRequiredFeatureDE(casType, "integer", "uima.cas.Boolean", featOkTst);
    casFeatCode_integer  = (null == casFeat_integer) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_integer).getCode();

 
    casFeat_zero_decimal = jcas.getRequiredFeatureDE(casType, "zero_decimal", "uima.cas.Boolean", featOkTst);
    casFeatCode_zero_decimal  = (null == casFeat_zero_decimal) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_zero_decimal).getCode();

 
    casFeat_unit = jcas.getRequiredFeatureDE(casType, "unit", "uima.tcas.Annotation", featOkTst);
    casFeatCode_unit  = (null == casFeat_unit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_unit).getCode();

 
    casFeat_source = jcas.getRequiredFeatureDE(casType, "source", "uima.cas.String", featOkTst);
    casFeatCode_source  = (null == casFeat_source) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_source).getCode();

 
    casFeat_timestamp = jcas.getRequiredFeatureDE(casType, "timestamp", "uima.tcas.Annotation", featOkTst);
    casFeatCode_timestamp  = (null == casFeat_timestamp) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_timestamp).getCode();

  }
}



    