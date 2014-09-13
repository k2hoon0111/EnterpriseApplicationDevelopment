package lv.javaguru.ee.bookshop.core.domain.audit;

import com.google.common.base.Objects;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "AUDIT_REVISIONS")
@RevisionEntity(AuditRevisionListener.class)
public class AuditRevision {

    @Id
//	@GeneratedValue(generator = "AUDIT_REVISIONS_SEQ", strategy = GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "AUDIT_REVISIONS_SEQ", sequenceName = "AUDIT_REVISIONS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @RevisionNumber
	private Long id;

	@Column(name = "REVISION_DATE")
	@RevisionTimestamp
	private Date revisionDate;

	
	public Long getId() {
		return id;
	}

	public Date getRevisionDate() {
		return revisionDate;
	}

	@Override
	public final int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof AuditRevision) {
			AuditRevision other = (AuditRevision) obj;
			Long thisId = this instanceof HibernateProxy ? this.getId() : this.id;
			Long otherId = other instanceof HibernateProxy ? other.getId() : other.id;
			return Objects.equal(thisId, otherId);
		} else {
			return false;
		}
	}

}
