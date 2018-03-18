
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WorkType {

    private String workType;

    public WorkType(){

    }

    public WorkType(String workType){
        this.workType = workType;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }
}
