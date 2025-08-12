package com.shivraj.model;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Student 
{
	private int id;
	@Nonnull
	private String name;
	@Nonnull
	private String subject;
	@Nonnull
	private double marks;
}
