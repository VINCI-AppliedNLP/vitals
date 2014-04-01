

/* First created by JCasGen Mon Mar 31 19:30:42 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 31 19:30:42 CDT 2014
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/2/leoTypeDescription_d87e2f92-16c1-4343-a6f5-b3ba255fe459678722988791812076.xml
 * @generated */
public class RefStAssessment extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(RefStAssessment.class);
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
  protected RefStAssessment() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public RefStAssessment(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public RefStAssessment(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public RefStAssessment(JCas jcas, int begin, int end) {
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
     
}

    