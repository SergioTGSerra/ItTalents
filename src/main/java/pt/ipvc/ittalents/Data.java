package pt.ipvc.ittalents;

import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Backend.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    public static int logedUserId;
    public static List<Person> persons = new ArrayList<>();

    public static Map<Skill, List<Person>> mapSkillsPersons = new HashMap<Skill, List<Person>>();
}
