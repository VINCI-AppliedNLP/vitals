

/* First created by JCasGen Fri Mar 21 14:22:46 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Mar 21 14:22:46 CDT 2014
 * XML source: C:/DOCUME~1/VH813C~1/LOCALS~1/Temp/8/leoTypeDescription_69298067-c664-443d-8742-efeb9a65fff31026889561703071086.xml
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

    