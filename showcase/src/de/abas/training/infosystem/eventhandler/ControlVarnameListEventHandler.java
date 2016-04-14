package de.abas.training.infosystem.eventhandler;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.ButtonEventHandler;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.annotation.FieldEventHandler;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi2.type.ButtonEventType;
import de.abas.erp.axi2.type.FieldEventType;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.ow1.ControlVarnameList;
import de.abas.erp.db.schema.company.Vartab;
import de.abas.erp.db.schema.company.Vartab.Row;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

@EventHandler(head = ControlVarnameList.class, row = ControlVarnameList.Row.class)
@RunFopWith(EventHandlerRunner.class)
public class ControlVarnameListEventHandler {

	@ScreenEventHandler(type = ScreenEventType.ENTER)
	public void screenEnter(ScreenEvent event, ScreenControl screenControl,
			DbContext ctx, ControlVarnameList head) throws EventException{
		screenControl.setInvisible(head, ControlVarnameList.META.yheadfields,
				true);
		screenControl.setInvisible(head, ControlVarnameList.META.ytabfields,
				true);
	}


	@ButtonEventHandler(field="start", type = ButtonEventType.AFTER)
	public void startAfter(ButtonEvent event, ScreenControl screenControl,
			DbContext ctx, ControlVarnameList head) throws EventException{
		Vartab yvartab = head.getYvartab();
		head.table().clear();
		if (yvartab == null) {
			throw new EventException("no vartab selected", 1);
		}

		Iterable<Row> rows = yvartab.table().getRows();
		for (Vartab.Row row : rows) {
			if (row.isInTab()) {
				if (head.getYtabfields() == true) {
					ControlVarnameList.Row appendRow = head.table().appendRow();
					appendRow.setYtengname(row.getVarNameEnglish());
					appendRow.setYtgername(row.getVarName());
					appendRow.setYtistabfield(row.getInTab());
				}
			}else {
				if (head.getYheadfields() == true) {
					ControlVarnameList.Row appendRow = head.table().appendRow();
					appendRow.setYtengname(row.getVarNameEnglish());
					appendRow.setYtgername(row.getVarName());
				}
			}
		}
	}


	@FieldEventHandler(field="yvartab", type = FieldEventType.EXIT)
	public void yvartabExit(FieldEvent event, ScreenControl screenControl,
			DbContext ctx, ControlVarnameList head) throws EventException{
		Vartab yvartab = head.getYvartab();
		head.setYvartabtext(yvartab);
		head.table().clear();

		if (yvartab == null) {
			return;
		}

		head.setYheadfields(true);
		screenControl.setInvisible(head, ControlVarnameList.META.yheadfields, false);
		if (yvartab.isGrpWithTab()) {
			screenControl.setInvisible(head, ControlVarnameList.META.ytabfields, false);
			head.setYtabfields(true);
		}else {
			screenControl.setInvisible(head, ControlVarnameList.META.ytabfields, true);
			head.setYtabfields(false);
		}
	}


	@FieldEventHandler(field="yvartabtext", type = FieldEventType.EXIT)
	public void yvartabtextExit(FieldEvent event, ScreenControl screenControl,
			DbContext ctx, ControlVarnameList head) throws EventException{
		Vartab yvartab = head.getYvartabtext();
		head.setYvartab(yvartab);
		head.table().clear();

		if (yvartab == null) {
			return;
		}

		head.setYheadfields(true);
		screenControl.setInvisible(head, ControlVarnameList.META.yheadfields, false);
		if (yvartab.isGrpWithTab()) {
			screenControl.setInvisible(head, ControlVarnameList.META.ytabfields, false);
			head.setYtabfields(true);
		}else {
			screenControl.setInvisible(head, ControlVarnameList.META.ytabfields, true);
			head.setYtabfields(false);
		}
	}
}
