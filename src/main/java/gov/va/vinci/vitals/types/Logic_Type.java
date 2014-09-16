
/* First created by JCasGen Tue Sep 16 10:45:16 CDT 2014 */
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
 * Updated by JCasGen Tue Sep 16 10:45:16 CDT 2014
 * @generated */
public class Logic_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Logic_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Logic_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Logic(addr, Logic_Type.this);
  			   Logic_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Logic(addr, Logic_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Logic.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.Logic");
 
  /** @generated */
  final Feature casFeat_VitalType;
  /** @generated */
  final int     casFeatCode_VitalType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getVitalType(int addr) {
        if (featOkTst && casFeat_VitalType == null)
      jcas.throwFeatMissing("VitalType", "gov.va.vinci.vitals.types.Logic");
    return ll_cas.ll_getStringValue(addr, casFeatCode_VitalType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setVitalType(int addr, String v) {
        if (featOkTst && casFeat_VitalType == null)
      jcas.throwFeatMissing("VitalType", "gov.va.vinci.vitals.types.Logic");
    ll_cas.ll_setStringValue(addr, casFeatCode_VitalType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_VitalTerm;
  /** @generated */
  final int     casFeatCode_VitalTerm;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getVitalTerm(int addr) {
        if (featOkTst && casFeat_VitalTerm == null)
      jcas.throwFeatMissing("VitalTerm", "gov.va.vinci.vitals.types.Logic");
    return ll_cas.ll_getRefValue(addr, casFeatCode_VitalTerm);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setVitalTerm(int addr, int v) {
        if (featOkTst && casFeat_VitalTerm == null)
      jcas.throwFeatMissing("VitalTerm", "gov.va.vinci.vitals.types.Logic");
    ll_cas.ll_setRefValue(addr, casFeatCode_VitalTerm, v);}
    
  
 
  /** @generated */
  final Feature casFeat_VitalValue;
  /** @generated */
  final int     casFeatCode_VitalValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getVitalValue(int addr) {
        if (featOkTst && casFeat_VitalValue == null)
      jcas.throwFeatMissing("VitalValue", "gov.va.vinci.vitals.types.Logic");
    return ll_cas.ll_getRefValue(addr, casFeatCode_VitalValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setVitalValue(int addr, int v) {
        if (featOkTst && casFeat_VitalValue == null)
      jcas.throwFeatMissing("VitalValue", "gov.va.vinci.vitals.types.Logic");
    ll_cas.ll_setRefValue(addr, casFeatCode_VitalValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ValueString;
  /** @generated */
  final int     casFeatCode_ValueString;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getValueString(int addr) {
        if (featOkTst && casFeat_ValueString == null)
      jcas.throwFeatMissing("ValueString", "gov.va.vinci.vitals.types.Logic");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ValueString);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setValueString(int addr, String v) {
        if (featOkTst && casFeat_ValueString == null)
      jcas.throwFeatMissing("ValueString", "gov.va.vinci.vitals.types.Logic");
    ll_cas.ll_setStringValue(addr, casFeatCode_ValueString, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Unit;
  /** @generated */
  final int     casFeatCode_Unit;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getUnit(int addr) {
        if (featOkTst && casFeat_Unit == null)
      jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Logic");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Unit);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setUnit(int addr, String v) {
        if (featOkTst && casFeat_Unit == null)
      jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Logic");
    ll_cas.ll_setStringValue(addr, casFeatCode_Unit, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Logic_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_VitalType = jcas.getRequiredFeatureDE(casType, "VitalType", "uima.cas.String", featOkTst);
    casFeatCode_VitalType  = (null == casFeat_VitalType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_VitalType).getCode();

 
    casFeat_VitalTerm = jcas.getRequiredFeatureDE(casType, "VitalTerm", "uima.tcas.Annotation", featOkTst);
    casFeatCode_VitalTerm  = (null == casFeat_VitalTerm) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_VitalTerm).getCode();

 
    casFeat_VitalValue = jcas.getRequiredFeatureDE(casType, "VitalValue", "uima.tcas.Annotation", featOkTst);
    casFeatCode_VitalValue  = (null == casFeat_VitalValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_VitalValue).getCode();

 
    casFeat_ValueString = jcas.getRequiredFeatureDE(casType, "ValueString", "uima.cas.String", featOkTst);
    casFeatCode_ValueString  = (null == casFeat_ValueString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ValueString).getCode();

 
    casFeat_Unit = jcas.getRequiredFeatureDE(casType, "Unit", "uima.cas.String", featOkTst);
    casFeatCode_Unit  = (null == casFeat_Unit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Unit).getCode();

  }
}



    