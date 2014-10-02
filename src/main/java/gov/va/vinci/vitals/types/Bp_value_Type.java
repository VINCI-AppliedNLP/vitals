
/* First created by JCasGen Thu Oct 02 11:45:54 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Oct 02 11:45:54 CDT 2014
 * @generated */
public class Bp_value_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Bp_value_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Bp_value_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Bp_value(addr, Bp_value_Type.this);
  			   Bp_value_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Bp_value(addr, Bp_value_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Bp_value.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.Bp_value");
 
  /** @generated */
  final Feature casFeat_systolicValue;
  /** @generated */
  final int     casFeatCode_systolicValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSystolicValue(int addr) {
        if (featOkTst && casFeat_systolicValue == null)
      jcas.throwFeatMissing("systolicValue", "gov.va.vinci.vitals.types.Bp_value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_systolicValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSystolicValue(int addr, String v) {
        if (featOkTst && casFeat_systolicValue == null)
      jcas.throwFeatMissing("systolicValue", "gov.va.vinci.vitals.types.Bp_value");
    ll_cas.ll_setStringValue(addr, casFeatCode_systolicValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_diastolicValue;
  /** @generated */
  final int     casFeatCode_diastolicValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDiastolicValue(int addr) {
        if (featOkTst && casFeat_diastolicValue == null)
      jcas.throwFeatMissing("diastolicValue", "gov.va.vinci.vitals.types.Bp_value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_diastolicValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDiastolicValue(int addr, String v) {
        if (featOkTst && casFeat_diastolicValue == null)
      jcas.throwFeatMissing("diastolicValue", "gov.va.vinci.vitals.types.Bp_value");
    ll_cas.ll_setStringValue(addr, casFeatCode_diastolicValue, v);}
    
  
 
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
      jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Bp_value");
    return ll_cas.ll_getRefValue(addr, casFeatCode_unit);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setUnit(int addr, int v) {
        if (featOkTst && casFeat_unit == null)
      jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Bp_value");
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
      jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Bp_value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_source);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSource(int addr, String v) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Bp_value");
    ll_cas.ll_setStringValue(addr, casFeatCode_source, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Bp_value_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_systolicValue = jcas.getRequiredFeatureDE(casType, "systolicValue", "uima.cas.String", featOkTst);
    casFeatCode_systolicValue  = (null == casFeat_systolicValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_systolicValue).getCode();

 
    casFeat_diastolicValue = jcas.getRequiredFeatureDE(casType, "diastolicValue", "uima.cas.String", featOkTst);
    casFeatCode_diastolicValue  = (null == casFeat_diastolicValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_diastolicValue).getCode();

 
    casFeat_unit = jcas.getRequiredFeatureDE(casType, "unit", "uima.tcas.Annotation", featOkTst);
    casFeatCode_unit  = (null == casFeat_unit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_unit).getCode();

 
    casFeat_source = jcas.getRequiredFeatureDE(casType, "source", "uima.cas.String", featOkTst);
    casFeatCode_source  = (null == casFeat_source) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_source).getCode();

  }
}



    