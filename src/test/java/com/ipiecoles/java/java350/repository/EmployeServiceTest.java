package com.ipiecoles.java.java350.repository;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.service.EmployeService;

@ExtendWith(MockitoExtension.class)
public class EmployeServiceTest {

	@InjectMocks
	EmployeService employeService;

	@Mock
	EmployeRepository employeRepository;

	@ParameterizedTest
	@MethodSource({ "employeTesting", "employeTestingNullCondition" })
	public void testEmployeServiceEmbaucheEmploye(String nom, String prenom, Poste poste, NiveauEtude niveauEtude,
			Double tempsPartiel, String messageExceptionAttendu, Class<?> classException) {
		try {
			employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
		} catch (Exception e) {
			Assertions.assertEquals(messageExceptionAttendu, e.getMessage());
			Assertions.assertEquals(classException, e.getClass());
		}
	}

	@ParameterizedTest

	private static Stream<Arguments> employeTestingNullCondition() {
		return Stream.of(
				Arguments.of(null, null, null, null, 1.0, "Impossible de créer un employé sans poste !",
						EmployeException.class));
	}

	private static Stream<Arguments> employeTesting() {
		return Stream.of(Arguments.of("Test", "Test", Poste.COMMERCIAL, NiveauEtude.BTS_IUT, 1.0,
				"Impossible de créer un employé sans poste !", EmployeException.class));
	}

}
