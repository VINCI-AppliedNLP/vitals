
/* First created by JCasGen Tue Apr 07 22:29:27 MDT 2020 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Type used to output predictions
 * Updated by JCasGen Tue Apr 07 22:29:27 MDT 2020
 * @generated */
public class Hr_Prediction_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Hr_Prediction.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.Hr_Prediction");
 
  /** @generated */
  final Feature casFeat_srcFVFeature;
  /** @generated */
  final int     casFeatCode_srcFVFeature;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSrcFVFeature(int addr) {
        if (featOkTst && casFeat_srcFVFeature == null)
      jcas.throwFeatMissing("srcFVFeature", "gov.va.vinci.vitals.types.Hr_Prediction");
    return ll_cas.ll_getRefValue(addr, casFeatCode_srcFVFeature);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSrcFVFeature(int addr, int v) {
        if (featOkTst && casFeat_srcFVFeature == null)
      jcas.throwFeatMissing("srcFVFeature", "gov.va.vinci.vitals.types.Hr_Prediction");
    ll_cas.ll_setRefValue(addr, casFeatCode_srcFVFeature, v);}
    
  
 
  /** @generated */
  final Feature casFeat_prediction;
  /** @generated */
  final int     casFeatCode_prediction;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPrediction(int addr) {
        if (featOkTst && casFeat_prediction == null)
      jcas.throwFeatMissing("prediction", "gov.va.vinci.vitals.types.Hr_Prediction");
    return ll_cas.ll_getStringValue(addr, casFeatCode_prediction);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPrediction(int addr, String v) {
        if (featOkTst && casFeat_prediction == null)
      jcas.throwFeatMissing("prediction", "gov.va.vinci.vitals.types.Hr_Prediction");
    ll_cas.ll_setStringValue(addr, casFeatCode_prediction, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Hr_Prediction_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_srcFVFeature = jcas.getRequiredFeatureDE(casType, "srcFVFeature", "uima.tcas.Annotation", featOkTst);
    casFeatCode_srcFVFeature  = (null == casFeat_srcFVFeature) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_srcFVFeature).getCode();

 
    casFeat_prediction = jcas.getRequiredFeatureDE(casType, "prediction", "uima.cas.String", featOkTst);
    casFeatCode_prediction  = (null == casFeat_prediction) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_prediction).getCode();

  }
}



    