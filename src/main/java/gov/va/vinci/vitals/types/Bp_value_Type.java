
/* First created by JCasGen Mon Sep 22 23:11:26 CDT 2014 */
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
 * Updated by JCasGen Mon Sep 22 23:11:26 CDT 2014
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
  final Feature casFeat_SystolicValue;
  /** @generated */
  final int     casFeatCode_SystolicValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSystolicValue(int addr) {
        if (featOkTst && casFeat_SystolicValue == null)
      jcas.throwFeatMissing("SystolicValue", "gov.va.vinci.vitals.types.Bp_value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_SystolicValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSystolicValue(int addr, String v) {
        if (featOkTst && casFeat_SystolicValue == null)
      jcas.throwFeatMissing("SystolicValue", "gov.va.vinci.vitals.types.Bp_value");
    ll_cas.ll_setStringValue(addr, casFeatCode_SystolicValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_DiastolicValue;
  /** @generated */
  final int     casFeatCode_DiastolicValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDiastolicValue(int addr) {
        if (featOkTst && casFeat_DiastolicValue == null)
      jcas.throwFeatMissing("DiastolicValue", "gov.va.vinci.vitals.types.Bp_value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_DiastolicValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDiastolicValue(int addr, String v) {
        if (featOkTst && casFeat_DiastolicValue == null)
      jcas.throwFeatMissing("DiastolicValue", "gov.va.vinci.vitals.types.Bp_value");
    ll_cas.ll_setStringValue(addr, casFeatCode_DiastolicValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Unit;
  /** @generated */
  final int     casFeatCode_Unit;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getUnit(int addr) {
        if (featOkTst && casFeat_Unit == null)
      jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Bp_value");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Unit);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setUnit(int addr, int v) {
        if (featOkTst && casFeat_Unit == null)
      jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Bp_value");
    ll_cas.ll_setRefValue(addr, casFeatCode_Unit, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Bp_value_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_SystolicValue = jcas.getRequiredFeatureDE(casType, "SystolicValue", "uima.cas.String", featOkTst);
    casFeatCode_SystolicValue  = (null == casFeat_SystolicValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SystolicValue).getCode();

 
    casFeat_DiastolicValue = jcas.getRequiredFeatureDE(casType, "DiastolicValue", "uima.cas.String", featOkTst);
    casFeatCode_DiastolicValue  = (null == casFeat_DiastolicValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_DiastolicValue).getCode();

 
    casFeat_Unit = jcas.getRequiredFeatureDE(casType, "Unit", "uima.tcas.Annotation", featOkTst);
    casFeatCode_Unit  = (null == casFeat_Unit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Unit).getCode();

  }
}



    