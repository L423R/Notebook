package com.example.notebook.controllers;

import com.example.notebook.entities.Note;
import com.example.notebook.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class NoteController {

    private NoteService service;
    private String sortDateChoose = "DESC1";

    @Autowired
    public void setService(NoteService service) {
        this.service = service;
    }

    @RequestMapping(path = "/")
    public String index(Model model){
        List<Note> notes = filterAndSort();
        model.addAttribute("notes",notes);
        return "index";
    }

    @PostMapping(path = "/save")
    public String save(@RequestParam String message, @RequestParam(required = false) Date dateStart, @RequestParam(required = false) Date dateEnd){

        Note note = new Note(message);
        if (dateStart!=null)
            note.setDateStart(dateStart);
        if (dateEnd!=null)
            note.setDateEnd(dateEnd);
        service.saveNote(note);
        System.out.println("DateStart: "+dateStart);
        System.out.println("DateEnd:  "+dateEnd);
        return "redirect:/";
    }

    @GetMapping(path = "/sort/{sortDate}")
    public String sortChoose(@PathVariable String sortDate){
        sortDateChoose = sortDate;
        return "redirect:/";
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("note",service.getNote(id));
        return "/operations/edit";
    }

    @PostMapping(path = "/update")
    public String update(@RequestParam Integer id, @RequestParam String message,
                         @RequestParam(value = "done", required = false) boolean done,
                         @RequestParam(value = "dateEnd",required = false) Date dateEnd){
        service.updateNote(id, message, done,dateEnd);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newNote(){
        return "operations/new";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        service.deleteNote(service.getNote(id));
        return "redirect:/";
    }

    @GetMapping("/week")
    public String notesInWeek(Model model){

        /*model.addAttribute("week",getCurrentWeek());*/
        model.addAttribute("notesMap",getCurrNotes());
        return "operations/week";
    }

    private List<Note> filterAndSort() {
        List<Note> notebook = null;
        switch (sortDateChoose) {
            case "ASC1":
                notebook = service.findAllByOrderByDateStartAsc();
                break;
            case "DESC1":
                notebook = service.findAllByOrderByDateStartDesc();
                break;
            case "ASC2":
                notebook = service.findAllByOrderByDateEndAsc();
                break;
            case "DESC2":
                notebook = service.findAllByOrderByDateEndDesc();
                break;
            case "ASC3":
                notebook = service.findNotesByDoneOrderByDateEndAsc(false);
                break;
            case "ASC4":
                notebook = service.findNotesByDoneOrderByDateEndAsc(true);
                break;
        }
        return notebook;
    }

    


    private Map<Date,List<Note>> getCurrNotes(){
        Map<Date,List<Note>> dateListMap = new LinkedHashMap<>();

        for (Date date : getCurrentWeek()){
            dateListMap.put(date,getListOfNoteForCurrentDate(date));
        }
        return  dateListMap;
    }

    private List<Date> getCurrentWeek(){
        int count = getFirstDayOfWeek(getDayOfWeek());
        /*SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM", Locale.getDefault());*/
        ArrayList<Date> stringDates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            stringDates.add(new Date(new Date().getYear(),new Date().getMonth(),count));
            count++;
        }
        return stringDates;
    }

    private List<Note> getListOfNoteForCurrentDate(Date date){

        List<Note> temp = new ArrayList<>();
        for (Note note : service.getAllNote()){
                if (isDateEqualsNoteDateAndNotDone(date,note)) {
                    temp.add(note);
                }
        }
        return temp;
    }

	
	private int getFirstDayOfWeek(int dayOfWeek){
		Date da = new Date();
        int i1 = da.getDate();
        if (dayOfWeek==1)
            dayOfWeek = 8;
        return i1 - (dayOfWeek - 2);
	}
	
	private int getDayOfWeek(){
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.setTimeZone(TimeZone.getDefault());
        return c.get(Calendar.DAY_OF_WEEK);
	}
	
	
	private boolean isDateEqualsNoteDateAndNotDone(Date date, Note note){
		return  !note.isDone() && (date.after(note.getDateStart()) && date.before(note.getDateEnd())
                                || myEqualsDates(date,note.getDateStart()) || myEqualsDates(date,note.getDateEnd()));
	}

    private boolean myEqualsDates(Date date1,Date date2){
        int year1 = date1.getYear();
        int month1 = date1.getMonth();
        int day1 = date1.getDate();

        int year2 = date2.getYear();
        int month2 = date2.getMonth();
        int day2 = date2.getDate();
        return year1 == year2 && month1 == month2 && day1 == day2;
    }

}
