package lv.javaguru.ee.deliveryagency.core.domain.audit;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.hibernate.proxy.HibernateProxy;

import com.google.common.base.Objects;

@Entity(name = "AUDIT_REVISIONS")
@RevisionEntity(AuditRevisionListener.class)
public class AuditRevision {

	@Id
	@GeneratedValue(generator = "AUDIT_REVISIONS_SEQ", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "AUDIT_REVISIONS_SEQ", sequenceName = "AUDIT_REVISIONS_SEQ", allocationSize = 1)
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
