package entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class OperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String authority;
    @Column
    private String operation;
    @Temporal(value = TemporalType.TIMESTAMP)
    //(value = TemporalType.DATE)
    private Date date;
    /*@Temporal(value = TemporalType.TIME)
    private Time time;*/

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   /* public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }*/

    @Override
    public String toString() {
        return "\n OperationLog{" +
                "authority='" + authority + '\'' +
                ", operation='" + operation + '\'' +
                ", date=" + date +
              //  ", time=" + time +
                "}";
    }

    public static class OperationLogBuilder {
        private String authority;
        private String operation;
        private Date date;
        private Time time;

        public static OperationLogBuilder aLog(){
            return new OperationLogBuilder();
        }

        public OperationLogBuilder withAuthority(String authority) {
            this.authority = authority;
            return this;
        }

        public OperationLogBuilder withOperation(String operation) {
            this.operation = operation;
            return this;
        }

        public OperationLogBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public OperationLogBuilder withTime(Time time) {
            this.time = time;
            return this;
        }

        public OperationLog build(){
            OperationLog log = new OperationLog();
            log.setAuthority(authority);
            log.setOperation(operation);
            log.setDate(date);
          //  log.setTime(time);
            return log;
        }
    }
}
