package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.ItemNotFoundException;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemCreationService {

    private Item item;

    @Autowired
    private ItemRepository itemRepository;

    public void addItem(UUID uuid, String name, Double price) {
        itemRepository.save(new Item(uuid, name, price));
    }
}
//    public void editItem(UUID uuid, Item item) {
//        if (!ObjectUtils.isEmpty(tutorial.getTitle())) {
//            if (!existsById(tutorial.getId())) {
//                throw new ItemNotFoundException("Cannot find Item with UUID: " + tutorial.getId());
//            }
//            tutorialRepository.save(tutorial);
//        } else {
//            BadResourceException exc = new BadResourceException("Failed to add Tutorial");
//            exc.addErrorMessage("Tutorial is null or empty");
//            throw exc;
//        }
//    }
//
//    public void deleteItem() {
//        public void deleteById(Long id) throws ResourceNotFoundException {
//            if (!existsById(id)) {
//                throw new ResourceNotFoundException("Cannot find tutorial with id: " + id);
//            } else {
//                tutorialRepository.deleteById(id);
//            }
//        }
//    }
//}
/*
@Service
public class TutorialService {

    @Autowired
    private final TutorialRepository tutorialRepository;

    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    public boolean existsById(Long id) {
        return tutorialRepository.existsById(id);
    }

    public Tutorial findById(Long id) throws ResourceNotFoundException {
        Tutorial tutorial = tutorialRepository.findById(id).orElse(null);
        if (tutorial == null) {
            throw new ResourceNotFoundException("Cannot find Tutorial with id: " + id);
        } else
            return tutorial;
    }

    public List<Tutorial> findAll() {
        return (List<Tutorial>) tutorialRepository.findAll();
    }

    public List<Tutorial> findAllByTitle(String title) {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle(title);
        Specification<Tutorial> spec = new TutorialSpecification(tutorial);

        List<Tutorial> tutorials = new ArrayList<>();
        tutorialRepository.findAll(spec).forEach(tutorials::add);
        return tutorials;
    }

    public List<Tutorial> findAllByPublished() {
        Tutorial tutorial = new Tutorial();
        tutorial.setPublished(true);
        Specification<Tutorial> spec = new TutorialSpecification(tutorial);

        List<Tutorial> tutorials = new ArrayList<>();
        tutorialRepository.findAll(spec).forEach(tutorials::add);
        return tutorials;
    }

    public Tutorial add(Tutorial tutorial) {
        //if (!ObjectUtils.isEmpty(tutorial)) {
//            if (tutorial.getId() != null && existsById(tutorial.getId())) {
//                throw new ResourceAlreadyExistsException("tutorial with id: " + tutorial.getId() + " already exists");
//            }
            return tutorialRepository.save(tutorial);
//        } else {
//            BadResourceException exc = new BadResourceException("Failed to add tutorial");
//            exc.addErrorMessage("tutorial is null or empty");
//            throw exc;
//        }
    }

    public void update(Tutorial tutorial) throws BadResourceException, ResourceNotFoundException {
        if (!ObjectUtils.isEmpty(tutorial.getTitle())) {
            if (!existsById(tutorial.getId())) {
                throw new ResourceNotFoundException("Cannot find Tutorial with id: " + tutorial.getId());
            }
            tutorialRepository.save(tutorial);
        } else {
            BadResourceException exc = new BadResourceException("Failed to add Tutorial");
            exc.addErrorMessage("Tutorial is null or empty");
            throw exc;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find tutorial with id: " + id);
        } else {
            tutorialRepository.deleteById(id);
        }
    }

    public Long count() {
        return tutorialRepository.count();
    }
}
 */