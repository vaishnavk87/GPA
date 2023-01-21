package ports.database;

import entities.account.Account.AccountFactory;
import entities.account.Archive.ArchiveFactory;
import entities.account.Semester.SemesterFactory;
import entities.assessment.Assessment.AssessmentFactory;
import entities.assessment.AssessmentInstance.AssessmentInstanceFactory;
import entities.course.Course.CourseFactory;
import entities.course.CourseEvent.CourseEventFactory;
import entities.course.Outline.OutlineFactory;
import entities.weightScheme.OrderedWeight;
import entities.weightScheme.SimpleWeight;
import entities.weightScheme.Weight;


public interface EntityFactory extends
        AccountFactory,
        SemesterFactory,
        ArchiveFactory,
        CourseFactory,
        CourseEventFactory,
        OutlineFactory,
        AssessmentFactory,
        AssessmentInstanceFactory,
        Weight.WeightFactory,
        SimpleWeight.SimpleWeightFactory,
        OrderedWeight.OrderedWeightFactory
{}
