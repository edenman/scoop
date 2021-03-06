package com.example.scoop.basics.ui.wizardsample;

import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.ControllerModule;
import com.example.scoop.basics.ui.DemosController;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

@ControllerModule(ConfirmationController.Module.class)
@EnterTransition(ForwardSlideTransition.class)
@ExitTransition(BackwardSlideTransition.class)
public class ConfirmationController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(ConfirmationController.class);
    }

    @dagger.Module(
            injects = ConfirmationController.class,
            addsTo = EnterLastNameController.Module.class,
            library = true
    )
    public static class Module {}

    private AppRouter appRouter;
    private WizardSession wizardSession;

    @Bind(R.id.first_name_text)
    TextView firstNameTextView;

    @Bind(R.id.last_name_text)
    TextView lastNameTextView;

    @Inject
    public ConfirmationController(AppRouter appRouter, WizardSession wizardSession) {
        this.appRouter = appRouter;
        this.wizardSession = wizardSession;
    }

    @Override
    protected int layoutId() {
        return R.layout.wizard_confirmation;
    }

    @Override
    public void attach(View view) {
        super.attach(view);

        firstNameTextView.setText(wizardSession.firstName);
        lastNameTextView.setText(wizardSession.lastName);
    }

    @Override
    public void detach(View view) {
        super.detach(view);
    }

    @OnClick(R.id.next_button)
    public void onClick() {
        appRouter.resetTo(DemosController.createScreen());
    }
}
