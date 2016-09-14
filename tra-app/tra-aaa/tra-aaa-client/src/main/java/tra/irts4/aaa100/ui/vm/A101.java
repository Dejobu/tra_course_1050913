package tra.irts4.aaa100.ui.vm;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import tra.irts4.aaa.ui.vm.dto.TraDTOList;
import tra.irts4.aaa.ui.vm.dto.TraSearchDTO;
import tra.irts4.aaa.ui.vm.dto.Train;


public class A101 extends SelectorComposer<Component> {

	private static final long serialVersionUID = 8272676029895136860L;

	/** 查詢條件 */
    private TraSearchDTO searchDTO;

    /** wire to combo box */
    @Wire
    private Combobox combobox;
    /** wire to text box  */
    @Wire
    private Textbox textbox;

    /** 手動輸入的查詢條件 */
    private String queryString;

    /** 假資料列表 */
    private TraDTOList traResult;
    
    /** 查詢結果 */
    private static List<Train> searchResultModel;
    /** 查詢結果回傳至zk */
	private static List<Train> trainModel;

	@Init
	public void init() {
		this.searchDTO = new TraSearchDTO();
		searchResultModel = new ArrayList<Train>();
		trainModel = new ArrayList<Train>();
	}
	
	/**
	 * 使用日期, 車次查詢
	 */
	@Command
	@NotifyChange("trainModel")
	public void search() {
		// get Train Model Data
		traResult = new TraDTOList();
		
		// startTime, endTime, no. is match?
		searchResultModel.clear();
		trainModel.clear();
		for( Train train: traResult.getTrainModel() )
		{
			if( searchDTO.getStartTime()!=null && searchDTO.getEndTime()!=null && searchDTO.getNo()!=null ) {
				if( !train.getStartTime().before(searchDTO.getStartTime()) && !train.getStartTime().after(searchDTO.getEndTime()) && train.getNo().equals(searchDTO.getNo()) )
					trainModel.add(train);
			}
			else if( searchDTO.getStartTime()!=null && searchDTO.getEndTime()!=null ) {
				if( !train.getStartTime().before(searchDTO.getStartTime()) && !train.getStartTime().after(searchDTO.getEndTime()) )
					trainModel.add(train);
			}
			else if( searchDTO.getNo()!=null ) {
				if( train.getNo().equals(searchDTO.getNo()) )
					trainModel.add(train);
			}
		}
		
		searchResultModel.addAll(trainModel);
	}
	
	/**
	 * 使用篩選條件查詢: 車種
	 * 方法一
	 */
    @Wire
    private Checkbox puyumaCheckbox;
    @Wire
    private Checkbox tarokoCheckbox;
    @Wire
    private Checkbox tzeChiangCheckbox;
    @Wire
    private Checkbox chuKuangCheckbox;
    @Wire
    private Checkbox fuHsingCheckbox;
    @Wire
    private Checkbox localTrainCheckbox;
    
	@Listen("onCheck = #puyumaCheckbox, #tarokoCheckbox, #tzeChiangCheckbox, #chuKuangCheckbox, #fuHsingCheckbox, #localTrainCheckbox")
	@NotifyChange("trainModel")
	public void choiceTrainType() {
		trainModel.clear();
		
		for( Train train: searchResultModel ) 
		{
			if( puyumaCheckbox.isChecked() && train.getType().equals(puyumaCheckbox.getLabel()) ) {
				trainModel.add(train);
			}
			if( tarokoCheckbox.isChecked() && train.getType().equals(tarokoCheckbox.getLabel()) ) {
				trainModel.add(train);
			}
			if( tzeChiangCheckbox.isChecked() && train.getType().equals(tzeChiangCheckbox.getLabel()) ) {
				trainModel.add(train);
			}
			if( chuKuangCheckbox.isChecked() && train.getType().equals(chuKuangCheckbox.getLabel()) ) {
				trainModel.add(train);
			}
			if( fuHsingCheckbox.isChecked() && train.getType().equals(fuHsingCheckbox.getLabel()) ) {
				trainModel.add(train);
			}
			if( localTrainCheckbox.isChecked() && train.getType().equals(localTrainCheckbox.getLabel()) ) {
				trainModel.add(train);
			}
		}
		
		BindUtils.postNotifyChange(null, null, trainModel, "*");
	}

	/**
	 * 使用篩選條件查詢: 運轉方式
	 * 方法二
	 */
    @Command
    @NotifyChange("transModel")
    public void choiceTransferMethod(@BindingParam("checkMark") boolean checkMark, @BindingParam("type") String type) {
    	if( checkMark ) {

    	} else {

    	}
    }

	/**
	 * 點選listitem查看班次停靠站
	 */
	@Command
	public void queryTra() {
		System.out.println("~~ select ~~");
	}

//	/**
//	 * 操作，編輯班次資訊
//	 */
//	public void editTra() {
//		System.out.println("~~~ on Edit ~~~");
//		
//	}

	public TraSearchDTO getSearchDTO() {
		return searchDTO;
	}

	public void setSearchDTO(TraSearchDTO searchDTO) {
		this.searchDTO = searchDTO;
	}

	public TraDTOList getTraResult() {
		return traResult;
	}

	public void setTraResult(TraDTOList traResult) {
		this.traResult = traResult;
	}

	public List<Train> getTrainModel() {
		return trainModel;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

}
