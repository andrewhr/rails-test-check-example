class API::EntriesController < API::ApiController
  def create
    Entry.create!(entry_params)
    head :ok
  end

  def destroy
    Entry.find(params.fetch(:id)).destroy!
    head :ok
  end

  private

  def entry_params
    params.require(:entry).permit(:value)
  end
end
