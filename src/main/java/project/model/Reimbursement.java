package project.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.awt.image.BufferedImage;

public class Reimbursement {

	private int id; // Primary Key
	private int amount;
	private LocalDateTime submitted;
	private LocalDateTime resolved; // this can be null
	private String description;
	private byte[] receipt;
	private int author;
	private int resolver; // this can be null
	private int statusId;
	private int typeId;

	public Reimbursement(int amount, LocalDateTime submitted, LocalDateTime resolved, String description,
			byte[] receipt, int author, int resolver, int statusId, int typeId) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimbursement(int id, int amount, LocalDateTime submitted, LocalDateTime resolved, String description,
			byte[] receipt, int author, int resolver, int statusId, int typeId) {
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDateTime getSubmitted() {
		return submitted;
	}

	public void setSubmitted(LocalDateTime submitted) {
		this.submitted = submitted;
	}

	public LocalDateTime getResolved() {
		return resolved;
	}

	public void setResolved(LocalDateTime resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + Arrays.toString(receipt) + ", author=" + author
				+ ", resolver=" + resolver + ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}

}
