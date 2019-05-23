package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        //Given
        TrelloListDto trelloList1 = new TrelloListDto("1", "Trello1", true);
        TrelloListDto trelloList2 = new TrelloListDto("2", "Trello2", true);
        TrelloListDto trelloList3 = new TrelloListDto("3", "Trello3", true);
        TrelloListDto trelloList4 = new TrelloListDto("4", "Trello4", true);

        List<TrelloListDto> lists1 = new ArrayList<>();
        List<TrelloListDto> lists2 = new ArrayList<>();

        lists1.add(trelloList1);
        lists1.add(trelloList4);

        lists2.add(trelloList2);
        lists2.add(trelloList3);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("TrelloBoard1", "123", lists1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("TrelloBoard2", "321", lists2);

        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(trelloBoardDto1);
        boardDtos.add(trelloBoardDto2);

        //When
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardDtos);

        //Then
        Assert.assertEquals(2, boards.size());
        Assert.assertEquals(2, boards.get(0).getLists().size());
        Assert.assertEquals(2, boards.get(1).getLists().size());
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Trello1", true);
        TrelloList trelloList2 = new TrelloList("2", "Trello2", true);
        TrelloList trelloList3 = new TrelloList("3", "Trello3", true);
        TrelloList trelloList4 = new TrelloList("4", "Trello4", true);

        List<TrelloList> lists1 = new ArrayList<>();
        List<TrelloList> lists2 = new ArrayList<>();

        lists1.add(trelloList1);
        lists1.add(trelloList4);

        lists2.add(trelloList2);
        lists2.add(trelloList3);

        TrelloBoard trelloBoard1 = new TrelloBoard("TrelloBoard1", "123", lists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("TrelloBoard2", "321", lists2);

        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(trelloBoard1);
        boards.add(trelloBoard2);

        //When
        List<TrelloBoardDto> boardDtos = trelloMapper.mapToBoardsDto(boards);

        //Then
        Assert.assertEquals(2, boardDtos.size());
        Assert.assertEquals(2, boardDtos.get(0).getLists().size());
        Assert.assertEquals(2, boardDtos.get(1).getLists().size());
    }

    @Test
    public void mapToList() {
        //Given
        TrelloListDto trelloList1 = new TrelloListDto("1", "Trello1", true);
        TrelloListDto trelloList2 = new TrelloListDto("2", "Trello2", true);
        TrelloListDto trelloList3 = new TrelloListDto("3", "Trello3", true);
        TrelloListDto trelloList4 = new TrelloListDto("4", "Trello4", true);

        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloList1);
        lists.add(trelloList2);
        lists.add(trelloList3);
        lists.add(trelloList4);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(lists);

        //Then
        Assert.assertEquals(4, trelloLists.size());
        Assert.assertEquals("Trello2", trelloLists.get(1).getName());
    }

    @Test
    public void mapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Trello1", true);
        TrelloList trelloList2 = new TrelloList("2", "Trello2", true);
        TrelloList trelloList3 = new TrelloList("3", "Trello3", true);
        TrelloList trelloList4 = new TrelloList("4", "Trello4", true);

        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList1);
        lists.add(trelloList2);
        lists.add(trelloList3);
        lists.add(trelloList4);

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(lists);

        //Then
        Assert.assertEquals(4, trelloListDtos.size());
        Assert.assertEquals("Trello3", trelloListDtos.get(2).getName());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TestCard", "Test Card 1", "Pos1", "123456");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals("TestCard", trelloCard.getName());
        Assert.assertEquals("Pos1", trelloCard.getPos());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("TestCard", "Test Card 1", "Pos1", "123456");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals("TestCard", trelloCardDto.getName());
        Assert.assertEquals("Pos1", trelloCardDto.getPos());
    }
}